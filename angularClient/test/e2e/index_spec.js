
/*var origFn = browser.driver.controlFlow().execute;

browser.driver.controlFlow().execute = function() {
  var args = arguments;

  // queue 100ms wait
  origFn.call(browser.driver.controlFlow(), function() {
    return protractor.promise.delayed(1000);
  });

  return origFn.apply(browser.driver.controlFlow(), args);
};*/

describe('bookmart', function() {
  it('should have a title', function() {
    browser.get('http://localhost:8080/jerseyAngular/app/index.html');
    expect(browser.getTitle()).toEqual('Bookstore');
    browser.sleep(1000);

    var todoListItems = element.all(by.repeater('book in vm.books'));
    expect(todoListItems.count()).toBeGreaterThan(1);
    browser.sleep(1000);
    todoListItems.first().click();
    todoListItems.last().click();

    element(by.model('vm.bookRequest.comment')).sendKeys('i need these books');
    browser.sleep(1000);
  });
});
