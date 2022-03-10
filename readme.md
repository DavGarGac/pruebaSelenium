
Proyecto para generar reports de Allure con pruebas de Selenium
===============================================================

Prerequisitos
-------------

Es necesario instalarse Allure en Windows y que esté accesible desde la consola.

Estos son los pasos a seguir:

	Instalamos scoop en powershell

	$ iwr -useb get.scoop.sh | iex

	Instalamos allure con scoop

	$ scoop install allure

Ejecucion
------------

`mvn clean test`
`allure generate --clean`
`allure serve`

Este ultimo comando levanta un servidor 'jetty' que ejecuta el html generado.

Para que se conserve el historial, hemos creado un fichero 'runWithTests.bat' esto conservará el historial de las pruebas.


Bibliografia:
-------------


https://docs.qameta.io/allure/

https://scoop.sh/
