# On stocke la date dans une variable
current_date=$(date +"%Y-%m-%d_%H-%M")

# On charge les corpus si pas déjà chargé :
hadoop fs -mkdir /corpus
hadoop fs -put corpus/selectionCourt.csv /corpus
hadoop fs -put corpus/selectionComplete.csv /corpus

# On créé le répertoire de sortie sur notre machine
mkdir ./output/etape5_$current_date

# On supprime le répertoire de sortie s'il existe déjà sur HDFS
hadoop fs -rm -r /etape5
hadoop fs -mkdir /etape5

# On compile le projet
./build_jar.sh

# On lance les jobs, on récupère les résultats et on supprime les fichiers de vérification CRC
# Pour illustrer on ne lance que les jobs pour le corpus selectionCourt.csv
# Ici en ignorant tous les status "Running" en triant sur la colonne 2
hadoop jar MyHadoopApps.jar TaskAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape5/etape5_1_court -sort 2 -ignore Running
hadoop fs -getmerge /etape5/etape5_1_court/part-r-* ./output/etape5_$current_date/tasks_court.csv
rm ./output/etape5_$current_date/.tasks_court.csv.crc

hadoop jar MyHadoopApps.jar JobAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape5/etape5_2_court -sort 1 -ignore Running
hadoop fs -getmerge /etape5/etape5_2_court/part-r-* ./output/etape5_$current_date/jobs_court.csv
rm ./output/etape5_$current_date/.jobs_court.csv.crc

hadoop jar MyHadoopApps.jar TaskAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape5/etape5_1_complete -sort 2 -ignore Running
hadoop fs -getmerge /etape5/etape5_1_complete/part-r-* ./output/etape5_$current_date/tasks_complete.csv
rm ./output/etape5_$current_date/.tasks_complete.csv.crc

hadoop jar MyHadoopApps.jar JobAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape5/etape5_2_complete -sort 1 -ignore Running
hadoop fs -getmerge /etape5/etape5_2_complete/part-r-* ./output/etape5_$current_date/jobs_complete.csv
rm ./output/etape5_$current_date/.jobs_complete.csv.crc

# Ici en séparant les résultats en deux fichiers pour les tasks avec moins de 500 instances et avec plus de 500 instances
hadoop jar MyHadoopApps.jar TaskAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape5/etape5_1_court_separate -separateFiles 1 2 0-500,500-99999999
hadoop fs -get /etape5/etape5_1_court_separate/output_0-r-00000 ./output/etape5_$current_date/tasks_court_0-500.csv
hadoop fs -get /etape5/etape5_1_court_separate/output_1-r-00000 ./output/etape5_$current_date/tasks_court_500-99999999.csv

hadoop jar MyHadoopApps.jar TaskAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape5/etape5_1_complete_separate -separateFiles 1 2 0-500,500-99999999
hadoop fs -get /etape5/etape5_1_complete_separate/output_0-r-00000 ./output/etape5_$current_date/tasks_complete_0-500.csv
hadoop fs -get /etape5/etape5_1_complete_separate/output_1-r-00000 ./output/etape5_$current_date/tasks_complete_500-99999999.csv

# Ici en séparant les résultats en trois fichiers pour les jobs avec moins de 3 tâches, entre 3 et 5 tâches et entre 5 et 100 tâches
hadoop jar MyHadoopApps.jar JobAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape5/etape5_2_court_separate -separateFiles 1 3 0-10,10-15,15-1000
hadoop fs -get /etape5/etape5_2_court_separate/output_0-r-00000 ./output/etape5_$current_date/jobs_court_0-10.csv
hadoop fs -get /etape5/etape5_2_court_separate/output_1-r-00000 ./output/etape5_$current_date/jobs_court_10-15.csv
hadoop fs -get /etape5/etape5_2_court_separate/output_2-r-00000 ./output/etape5_$current_date/jobs_court_15-1000.csv

hadoop jar MyHadoopApps.jar JobAnalysisDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape5/etape5_2_complete_separate -separateFiles 1 3 0-10,10-15,15-1000
hadoop fs -get /etape5/etape5_2_complete_separate/output_0-r-00000 ./output/etape5_$current_date/jobs_complete_0-10.csv
hadoop fs -get /etape5/etape5_2_complete_separate/output_1-r-00000 ./output/etape5_$current_date/jobs_complete_10-15.csv
hadoop fs -get /etape5/etape5_2_complete_separate/output_2-r-00000 ./output/etape5_$current_date/jobs_complete_15-1000.csv

