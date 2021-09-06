searchDir="properties/*.properties"
for file in $searchDir
do
  FILENAME=`basename ${file%%.*}`; echo ${FILENAME};
  oc replace cm country-"${FILENAME}" --from-env-file=$file
  cp openshift.yml openshift-"${FILENAME}".yml
  sed -i "" 's/greeting-app/greeting-app-'"$FILENAME"'/g' openshift-${FILENAME}.yml
  sed -i "" 's,anasandbox/greeting-app-'"$FILENAME"',anasandbox/greeting-app,g' openshift-${FILENAME}.yml
  sed -i "" 's,greeting-app-'"$FILENAME"':1.0-SNAPSHOT,greeting-app:1.0-SNAPSHOT,g' openshift-${FILENAME}.yml

  oc replace -f openshift-${FILENAME}.yml
  oc set env --from=configmap/country-"${FILENAME}" dc/greeting-app-"${FILENAME}"
done