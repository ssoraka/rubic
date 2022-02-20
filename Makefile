
# https://maven.apache.org/download.cgi
# curl https://apache-mirror.rbc.ru/pub/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz --output apache-maven-3.6.3-bin.tar.gz
# tar -xf apache-maven-3.6.3-bin.tar.gz
# export PATH=/goinfre/ssoraka/apache-maven-3.6.3/bin:$PATH

NAME = ./target/rubik.jar


all: $(NAME)

$(NAME):
	mvn clean install package

clean:
	mvn clean

fclean:
	mvn clean

re: fclean all


