(function(){
    angular.module('bookmart').controller('bookShelfCtrl', bookShelfCtrl);
    bookShelfCtrl.$inject = ['$scope', '$http', 'BASE_URL'];
    function bookShelfCtrl($scope, $http, BASE_URL) {
        var vm = this;

        vm.name = "book shelf controller";

        vm.books = [];
        vm.bookRequest = {
            guids:[],
            comment: ""
        };

        vm.submit = submit;

        $http.get(BASE_URL + '/book/0').then(function(response) {
            vm.books = response.data;
            vm.books.map(function(book) {
                book.isSelected = false;
            });
        });

        function submit(request) {
            vm.bookRequest.guids = vm.books.filter(function(book){return book.isSelected;}).map(function(book){return book.guid});
            $http.post(BASE_URL + '/book/request', vm.bookRequest).then(function(response) {
                console.log(response.status);
            });
        }
    }
})();