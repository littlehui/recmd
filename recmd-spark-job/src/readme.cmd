****离线执行的实例****
/opt/17173/hadoop-2016/spark-2.0.0-bin-hadoop2.7/bin/spark-submit --master yarn  --deploy-mode client --name VisitCountJob --class com.xhui.recmd.spark.job.VisitCountJob --executor-memory 1G --total-executor-cores 1  /opt/17173/hadoop-2016/sparkjob/recmd-spark-job-1.0-SNAPSHOT.jar 20160918/20160918_*.txt
****删除****
hadoop fs -rmr /spark/job/result/20160918.result
****上传日志到hdfs****
hadoop fs -mkdir /spark/job/data/20160918
hadoop fs -put /opt/17173/hadoop-2016/sparkjob/data/20160918/20160918_*.txt /spark/job/data/20160918

*****查看清洗后的日志数据*****
scan 'visitCount'
scan 'classifier'