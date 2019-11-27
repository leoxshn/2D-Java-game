run:
	javac -d out posidon/pixelium/Pixelium.java
	cd out && java posidon.pixelium.Pixelium
clean:
	find . -type f -name '*.class' -delete
	find . -type f -name '*.jar' -delete
jar:
	javac -d out posidon/pixelium/Pixelium.java
	cd out && jar cvfe pixelium.jar posidon.pixelium.Pixelium posidon/pixelium/*
	chmod +x out/pixelium.jar
	cd && java -jar /media/leo/posidon/programing/pixelium/out/pixelium.jar
