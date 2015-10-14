(function () {
    angular.module('bookmart', ['ngRoute', 'ui.bootstrap']);
    angular.module('bookmart').config(config);
    config.$inject=['$routeProvider'];
    function config($routeProvider) {
        $routeProvider
            .when('/bookmart', {
                templateUrl: 'bookstore/bookshelf.html',
                controller: 'bookShelfCtrl',
                controllerAs: 'vm'
            })
            .when('/request', {
                 templateUrl: 'bookstore/request.html'
            })
            .otherwise({
                redirectTo: '/bookmart'
            });
    }
})();