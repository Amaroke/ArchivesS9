rm -r ./output/etape1
mkdir ./output/etape1
hadoop fs -rm -r /etape1
hadoop fs -mkdir /etape1
hadoop fs -mkdir /corpus
hadoop fs -put corpus/selectionCourt.csv /corpus
hadoop fs -put corpus/selectionComplete.csv /corpus
./build_jar.sh
hadoop jar MyHadoopApps.jar TaskAnalysisDriver /corpus/selectionCourt.csv /etape1/etape1_1_court
hadoop jar MyHadoopApps.jar TaskAnalysisDriver /corpus/selectionComplete.csv /etape1/etape1_1_complete
hadoop jar MyHadoopApps.jar JobAnalysisDriver /corpus/selectionCourt.csv /etape1/etape1_2_court
hadoop jar MyHadoopApps.jar JobAnalysisDriver /corpus/selectionComplete.csv /etape1/etape1_2_complete
hadoop fs -get /etape1/etape1_1_court/part-r-00000 ./output/etape1/etape1_1_court.csv
hadoop fs -get /etape1/etape1_1_complete/part-r-00000 ./output/etape1/etape1_1_complete.csv
hadoop fs -get /etape1/etape1_2_court/part-r-00000 ./output/etape1/etape1_2_court.csv
hadoop fs -get /etape1/etape1_2_complete/part-r-00000 ./output/etape1/etape1_2_complete.csv