var app = angular.module("app", ["ngRoute"]);
var sesion_logada = "";
var mail_logado="";
var part_cargada="";
var codequi_cargada="";
var equi_cargada="";


app.config(function($routeProvider) {
    $routeProvider
    .when("/acceso", {
        templateUrl : "vistas/listados/acceso.html",
        controller: 'Controlador_acceso',
        vble_panta: 'acceso'
    })
    .when("/menu", {
        templateUrl : "vistas/listados/menu.html",
        controller: 'Controlador_menu',
        vble_panta: 'menu'
    })
    .when("/coches", {
        templateUrl : "vistas/listados/coches.html",
        controller: 'Controlador_coche',
        vble_panta: 'coche'
    })
    .when("/partidas", {
        templateUrl : "vistas/listados/partidas.html",
        controller: 'Controlador_partidas',
        vble_panta: 'partidas'
    })
    .when("/logout", {
        templateUrl : "vistas/listados/acceso.html",
        controller: 'Controlador_logout',
        vble_panta: 'logout'
    })    
 .when("/error", {
        templateUrl : "vistas/listados/404.html"
    })    
     .otherwise({
        redirect: '/vistas/listados/404.html'
     }) ;
});
//////////////////////
app.controller("GenController",['$scope','$log','$http',function($scope,$log,$http) {
	$scope.mensaje="HOLA";
	$scope.email=mail_logado;
	$scope.partida_cargada=part_cargada;
}]);

//////////////////////
app.controller("Controlador_menu",
		['$scope', '$location', '$http', '$route', '$sce', '$httpParamSerializerJQLike',
			 function ($scope, $location, $http, $route, $sce, $httpParamSerializerJQLike){   
	$scope.mensaje="Pagina Menu";
	
	$scope.email=mail_logado;
	$scope.partida_cargada=part_cargada;
	$scope.equipo=equi_cargada;
	//alert('values:'+mail_logado+part_cargada+' '+codequi_cargada+' '+equi_cargada);
	//alert('values2:'+$scope.email+$scope.partida_cargada+$scope.equipo);
	
	haylogin('/menu',$scope, $location, $http, $route, $sce, $httpParamSerializerJQLike);
	

}]);

//////////////////////
app.controller("Controlador_partidas",
		['$scope', '$location', '$http', '$route', '$sce', '$httpParamSerializerJQLike',
			 function ($scope, $location, $http, $route, $sce, $httpParamSerializerJQLike){ 
	$scope.mensaje="Pagina partidas";
	//alert("dentro de partidas"+sesion_logada);
	 haylogin('/partidas',$scope, $location, $http, $route, $sce, $httpParamSerializerJQLike);	
	 
	 $scope.email=mail_logado;
	 $scope.partida_cargada=part_cargada;
	 $scope.equipo=equi_cargada;
	  	 
	 $http({
		    method: 'get', 
		    url: 'servicios/partidas'
		}).then(function (response) {
		    console.log(response, 'res partidas');
		    data = response.data;		   
		    $scope.partidas= data;
		},function (error){
		    console.log(error, 'can not get data.');
		    alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	 
	
	  $scope.cargarPartida = function(codp,codcode,code) {
		  part_cargada=codp;
		  codequi_cargada=codcode;
		  equi_cargada=code;
		  $location.path($sce.trustAsResourceUrl('/menu'));
	  }
	  
	  
	  $scope.fu_accion_sql = function() {
		  $http({
			    method: 'get', 
			    url: 'servicios/partidas/sql'
			});
	  }
}]);




//////////////////////	
app.controller("Controlador_acceso",
		
			['$scope', '$location', '$http', '$route', '$sce', '$httpParamSerializerJQLike',
				 function ($scope, $location, $http, $route, $sce, $httpParamSerializerJQLike){
	$scope.mensaje="Pagina Acceso";
	
    $scope.count = 0;
    $scope.hacer_login = function(email,password) {
        $scope.count++;
        mail_logado=email;
        $scope.email=mail_logado;
        $scope.password=password;
        
        $http( {
    		method: 'POST',
    		url: 'servicios/user/hlogin',
    		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    		data: $httpParamSerializerJQLike({				
    			'email': email,
    			'password': password
    		})
    		 
    	} ).then(function( sesid ) {
    		  //Volvemos al listado		
    		//alert(JSON.stringify(sesid));
    		
    		if (sesid.data == ''){
    		//	alert(JSON.stringify('if'+sesid.data));
    			$location.path($sce.trustAsResourceUrl('/error'));}
    		else{
    		//	alert(JSON.stringify('else'+sesid.data));
    			sesion_logada=sesid.data;
    			$location.path($sce.trustAsResourceUrl('/partidas'));}
    			
    	 })       
        
         	 
           
           
    };
	
	

	
}]);
	
 

	function haylogin(ruta,$scope, $location, $http, $route, $sce, $httpParamSerializerJQLike) {
		//alert("dentro de haylogin1:"+sesion_logada+' ruta:'+ruta);
		$http( {
			method: 'POST',
			url: 'servicios/user/rlogin',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			data: $httpParamSerializerJQLike({				
				'sesion_logada': sesion_logada
			})
			 
		} ).then(function( sesid ) {
	  	  //Volvemos al listado		
			//alert(JSON.stringify(sesid));
			
			if (sesid.data == ''){
			//	alert(JSON.stringify('if'+sesid.data));
				$location.path($sce.trustAsResourceUrl('/error'));}
			else{
				//alert(JSON.stringify('else'+sesid.data));
				
				$location.path($sce.trustAsResourceUrl(ruta));}			
	     })
}


	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	app.controller("Controlador_logout",
			['$scope', '$location', '$http', '$route', '$sce', '$httpParamSerializerJQLike',
				 function ($scope, $location, $http, $route, $sce, $httpParamSerializerJQLike){   
	
		
		$scope.email=null;
		$scope.partida_cargada=null;
		$scope.equipo=null;		
		haylogin('/partidas',$scope, $location, $http, $route, $sce, $httpParamSerializerJQLike);
		

	}]);
	
	
	app.controller("Controlador_coche",['$scope','$log','$http',function($scope,$log,$http) {
		
		$scope.visitas=0;
		 $scope.visitas= $scope.visitas+1;	
		 $scope.mensaje="Pagina Coches";	
		
		 
		 $scope.personas=[
			 {nombre:"pepe",edad:20},
		     {nombre:"angel",edad:30},
		     {nombre:"maria",edad:35},
		     {nombre:"gema",edad:25}];
		 
		
		 
		 $http({
			    method: 'get', 
			    url: 'servicios/coches'
			}).then(function (response) {
			    console.log(response, 'res coches');
			    data = response.data;		  
			    $scope.coches= data;
			},function (error){
			    console.log(error, 'can not get data.');
			    alert("Ha fallado la petición. Estado HTTP:"+status);
			});
		 
		
	}]);
