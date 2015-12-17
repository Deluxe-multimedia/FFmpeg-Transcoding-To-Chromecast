# FFmpeg-Transcoding-Chromecast
A complete example usgin ffmpeg to transcoding video in real time to use in chromecast

## Español 100%, ¿Por qué?
Hay que legitimar el desarrollo de la gente hispanohablante, cada vez más pujante y partícipe del desarrollo del software libre. 


## ¿Qué se pretende con este proyecto?
Básicamente es un ejemplo. Para nada está pensado aún para entornos de producción porque necesita muchas optimizaciones y mejoras. Pero sirve como ejemplo de cómo mezclar una compilación de FFmpeg con un servidor web (NanoHTTPD) para hacer conversión de video en tiempo real (transcoding) y servirlo a través de una url que podremos enviar a nuestro Chromecast. De esta manera salvamos las distancias entre los formatos compatibles de Chromecast y los diferentes formatos que hay activos en el mundo multimedia

##¿Cómo usarlo?
Creo que el proyecto es lo demasiado simple y autoexplicativo como para no necesitar documentar nada. Nos encontramos con una pantalla donde introducir una url y un botón para activar el webserver. A partir de ahí nos indica donde está activa la url del streaming que servirá para VLC, Chromecast, etc.

##Ejemplo
Les dejo un apk compilado para probar antes de nada, por si os parece bien https://github.com/munix/FFmpeg-Transcoding-To-Chromecast/blob/master/APK/app-release.apk?raw=true

##Proyectos que me han inspirado o ayudado mucho (o que utilizo parcialmente). GRACIAS!
https://github.com/crust87/Android-FFmpegExecutor <br>
https://github.com/WritingMinds/ffmpeg-android-java <br>
https://github.com/appunite/AndroidFFmpeg <br>
https://github.com/NanoHttpd/nanohttpd <br>
http://www.sample-videos.com/index.php#sample-flv-video

<b>Siéntete libre de colaborar, mejorar o descartar este proyecto. Eres libre! Se aceptan pull requests</b>


