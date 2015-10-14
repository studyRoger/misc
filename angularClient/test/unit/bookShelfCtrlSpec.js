describe('bookShelfCtrlSpec', function () {
    beforeEach(module('bookmart'));

    it('should has a controller name', inject(function ($controller, $rootScope) {
      var $scope = $rootScope.$new();
      var bookShelfCtrl = $controller('bookShelfCtrl', {
        $scope: $scope
      });
      expect(bookShelfCtrl.name).toEqual('book shelf controller');

    }));
});