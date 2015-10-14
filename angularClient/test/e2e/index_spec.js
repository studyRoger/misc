describe('bookmart', function() {


  it('should have a title', function() {
    browser.get('http://localhost:8080/jerseyAngular/app/index.html');
    expect(browser.getTitle()).toEqual('Bookstore');
    browser.sleep(1000);

    var todoListItems = element.all(by.repeater('book in vm.books'));
    expect(todoListItems.count()).toBeGreaterThan(1);
    browser.sleep(1000);
    todoListItems.first().element(by.model('book.isSelected')).click();
    todoListItems.last().element(by.model('book.isSelected')).click();

    var selectedCount = element(by.exactBinding('(vm.books | filter : {isSelected: true}).length'));
    expect(selectedCount.isPresent()).toBe(true);
    expect(selectedCount.getText()).toEqual('selected books: 2');
    browser.sleep(1000);

    element(by.model('vm.bookRequest.comment')).sendKeys('i need these books');

    element(by.id('submit-request')).click().then(function() {
      return browser.waitForAngular();
    }).then(function() {
      expect(browser.getCurrentUrl()).toContain('/request');
      browser.sleep(1000);
    });





  });
});
