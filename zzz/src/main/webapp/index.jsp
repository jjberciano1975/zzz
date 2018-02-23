<!DOCTYPE html>
<html ng-app="app">
 
  <head>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script>
    <script src="js/script.js"></script>
  
  </head>
 
  <body ng-controller="GenController">
    <h1>{{mensaje}}</h1>


      <table>
        <tr ng-repeat="coche in coches">
           <td> {{coche.marca}}</td>  
           <td> {{coche.modelo}}</td>   
             <td> {{coche.iduser}}</td>                    
        </tr>

        </table>
   
    
      <table>
        <tr ng-repeat="persona in personas">
           <td> {{persona.nombre}}</td>
           <td>{{persona.edad}}</td>
           <td><input type="button" ng-click="consola(persona)"/>
        </tr>

        </table>
   
   		
   				

					
  </body>
 
</html>