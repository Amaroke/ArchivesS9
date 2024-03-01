# On stocke la date dans une variable
current_date=$(date +"%Y-%m-%d_%H-%M")

# On charge les corpus si pas déjà chargé :
hadoop fs -mkdir /corpus
hadoop fs -put corpus/selectionCourt.csv /corpus
hadoop fs -put corpus/selectionComplete.csv /corpus

# On créé le répertoire de sortie sur notre machine
mkdir ./output/etape3_$current_date

# On supprime le répertoire de sortie s'il existe déjà sur HDFS
hadoop fs -rm -r /etape3
hadoop fs -mkdir /etape3

# On compile le projet
./build_jar.sh

# On lance les jobs, on récupère les résultats et on supprime les fichiers de vérification CRC
ère les résultats
hadoop jar MyHadoopApps.jar CoreEstimationDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape3/etape3_court 
hadoop fs -getmerge /etape3/etape3_court/part-r-* ./output/etape3_$current_date/etape3_court.csv
rm ./output/etape3_$current_date/.etape3_court.csv.crc

hadoop jar MyHadoopApps.jar CoreEstimationDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape3/etape3_complete
hadoop fs -getmerge /etape3/etape3_complete/part-r-* ./output/etape3_$current_date/etape3_complete.csv
rm ./output/etape3_$current_date/.etape3_complete.csv.crc