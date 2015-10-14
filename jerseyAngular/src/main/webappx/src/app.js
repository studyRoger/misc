(function () {
    angular.module('bookmart', ['ngRoute', 'ui.bootstrap']);
    angular.module('bookmart').constant("BASE_URL", 'http://localhost:8080/jerseyAngular/rs');
    angular.module('bookmart').config(function ($routeProvider) {
        $routeProvider
            .when('/bookmart', {
                templateUrl: 'view/bookshelf.html',
                controller: 'bookShelfCtrl',
                controllerAs: 'vm'
            })
            .otherwise({
                redirectTo: '/bookmart'
            });
    });
})();
