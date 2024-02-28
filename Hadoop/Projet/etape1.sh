# On stocke la date dans une variable
current_date=$(date +"%Y-%m-%d_%H-%M")

# On charge les corpus si pas déjà chargé :
hadoop fs -mkdir /corpus
hadoop fs -put corpus/selectionCourt.csv /corpus
hadoop fs -put corpus/selectionComplete.csv /corpus

# On créé le répertoire de sortie sur notre machine
mkdir ./output/etape1_$current_date

# On supprime le répertoire de sortie s'il existe déjà sur HDFS
hadoop fs -rm -r /etape1
hadoop fs -mkdir /etape1

# On compile le projet
./build_jar.sh

# On lance les jobs et on récupère les résultats
hadoop jar MyHadoopApps.jar TaskAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape1/etape1_1_court 
hadoop fs -getmerge /etape1/etape1_1_court/part-r-* ./output/etape1_$current_date/etape1_1_court.csv

hadoop jar MyHadoopApps.jar TaskAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape1/etape1_1_complete
hadoop fs -getmerge /etape1/etape1_1_complete/part-r-* ./output/etape1_$current_date/etape1_1_complete.csv

hadoop jar MyHadoopApps.jar JobAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape1/etape1_2_court
hadoop fs -getmerge /etape1/etape1_2_court/part-r-* ./output/etape1_$current_date/etape1_2_court.csv

hadoop jar MyHadoopApps.jar JobAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape1/etape1_2_complete
hadoop fs -getmerge /etape1/etape1_2_complete/part-r-* ./output/etape1_$current_date/etape1_2_complete.csv

# On supprime les fichiers de vérification CRC
rm ./output/etape1_$current_date/.etape1_1_court.csv.crc
rm ./output/etape1_$current_date/.etape1_2_court.csv.crc
rm ./output/etape1_$current_date/.etape1_1_complete.csv.crc
rm ./output/etape1_$current_date/.etape1_2_complete.csv.crc