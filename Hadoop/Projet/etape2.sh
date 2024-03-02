# On stocke la date dans une variable
current_date=$(date +"%Y-%m-%d_%H-%M")

# On charge les corpus si pas déjà chargé :
hadoop fs -mkdir /corpus
hadoop fs -put corpus/selectionCourt.csv /corpus
hadoop fs -put corpus/selectionComplete.csv /corpus

# On créé le répertoire de sortie sur notre machine
mkdir ./output/etape2_$current_date

# On supprime le répertoire de sortie s'il existe déjà sur HDFS
hadoop fs -rm -r /etape2
hadoop fs -mkdir /etape2

# On compile le projet
./build_jar.sh

# On lance les jobs et on récupère les résultats
hadoop jar MyHadoopApps.jar CpuUsageAnalysisDriver /corpus/selectionCourt.csv /etape2/etape2_court 
hadoop fs -get /etape2/etape2_court/part-r-00000 ./output/etape2_$current_date/etape2_court.csv

: ' ATTENTION TRES LONG
hadoop jar MyHadoopApps.jar CpuUsageAnalysisDriver /corpus/selectionComplete.csv /etape2/etape2_complete
hadoop fs -get /etape2/etape2_complete/part-r-00000 ./output/etape2_$current_date/etape2_complete.csv
'