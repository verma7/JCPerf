if [ $# != 2 ]
then
	echo "Usage: $0 <from_2^> <to_2^>"
	exit
fi

FROM=$1
TO=$2
DATA_DIR=/tmp/local/dir
FMT=%E:%S:%U
TIME_CMD_CPP=$'/usr/bin/time -f \'CPP;%E;%S;%U\''
TIME_CMD_JAVA=$'/usr/bin/time -f \'JAVA;%E;%S;%U\''

mkdir -p $DATA_DIR

CUR_DIR=`echo $PWD`
CPP="../C++/microbenchmarks/disk/"
JAVA_DIR="../Java/bin"
JAVA="java microbenchmarks.disk."
BUFFER=1024000

for i in `seq $FROM $TO`
do
	size=`echo 2^$i|bc`
	$TIME_CMD_CPP ${CPP}Write $DATA_DIR/test.cpp.$i $size
	cd $JAVA_DIR
	$TIME_CMD_JAVA ${JAVA}WriteChannel $DATA_DIR/test.java.$i $size $BUFFER
	cd $CUR_DIR
done
cat /proc/meminfo
echo "echo 3 > /proc/sys/vm/drop_caches" | /tmp/local/setuid
cat /proc/meminfo
for i in `seq $FROM $TO`
do
	$TIME_CMD_CPP ${CPP}Read $DATA_DIR/test.cpp.$i
	cd $JAVA_DIR
	$TIME_CMD_JAVA ${JAVA}ReadChannel $DATA_DIR/test.java.$i $BUFFER
	cd $CUR_DIR
done
