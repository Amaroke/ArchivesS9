# On stocke la date dans une variable
current_date=$(date +"%Y-%m-%d_%H-%M")

# On charge les corpus si pas déjà chargé :
hadoop fs -mkdir /corpus
hadoop fs -put corpus/selectionCourt.csv /corpus
hadoop fs -put corpus/selectionComplete.csv /corpus

# On créé le répertoire de sortie sur notre machine
mkdir ./output/etape4_$current_date

# On supprime le répertoire de sortie s'il existe déjà sur HDFS
hadoop fs -rm -r /etape4
hadoop fs -mkdir /etape4

# On compile le projet
./build_jar.sh

# On lance les jobs, on récupère les résultats et on supprime les fichiers de vérification CRC
hadoop jar MyHadoopApps.jar MemoryRatioDriver -D mapreduce.job.reduces=2 /corpus/selectionCourt.csv /etape4/etape4_court 
hadoop fs -getmerge /etape4/etape4_court/part-r-* ./output/etape4_$current_date/etape4_court.csv
rm ./output/etape4_$current_date/.etape4_court.csv.crc

: ' ATTENTION FICHIER ÉNORME EN SORTIE
hadoop jar MyHadoopApps.jar MemoryRatioDriver -D mapreduce.job.reduces=2 /corpus/selectionComplete.csv /etape4/etape4_complete
hadoop fs -getmerge /etape4/etape4_complete/part-r-* ./output/etape4_$current_date/etape4_complete.csv
rm ./output/etape4_$current_date/.etape4_complete.csv.crc
'