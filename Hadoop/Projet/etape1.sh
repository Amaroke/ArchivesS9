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

# On lance les jobs, on récupère les résultats et on supprime les fichiers de vérification CRC
hadoop jar MyHadoopApps.jar TaskAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape1/etape1_1_court
hadoop fs -getmerge /etape1/etape1_1_court/part-r-* ./output/etape1_$current_date/tasks_court.csv
rm ./output/etape1_$current_date/.tasks_court.csv.crc

hadoop jar MyHadoopApps.jar JobAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape1/etape1_2_court
hadoop fs -getmerge /etape1/etape1_2_court/part-r-* ./output/etape1_$current_date/jobs_court.csv
rm ./output/etape1_$current_date/.jobs_court.csv.crc

hadoop jar MyHadoopApps.jar TaskAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape1/etape1_1_complete
hadoop fs -getmerge /etape1/etape1_1_complete/part-r-* ./output/etape1_$current_date/tasks_complete.csv
rm ./output/etape1_$current_date/.tasks_complete.csv.crc

hadoop jar MyHadoopApps.jar JobAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape1/etape1_2_complete
hadoop fs -getmerge /etape1/etape1_2_complete/part-r-* ./output/etape1_$current_date/jobs_complete.csv
rm ./output/etape1_$current_date/.jobs_complete.csv.crc