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
            .otherwise({
                redirectTo: '/bookmart'
            });
    }
})();