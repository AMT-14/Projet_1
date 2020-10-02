Feature('My First Tests');

Scenario('test something', ({ I }) => {
  I.amOnPage('https://github.com');
  I.see('GitHub');
});

Scenario('Login user with valid credentials', ({ I }) => {
    I.amOnPage('https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login');
    I.see('LOGIN Panel');
    I.fillField('txtUsername','opensourcecms'); //Can use the ID of an element to locate elements
    I.fillField('txtPassword','opensourcecms');
    I.click('LOGIN'); //Can use the text in the buttons to locate elements
    I.see('Welcome Admin'); //Assertion

});

Scenario('Login user with invalid credentials ', ({ I }) => {
    I.amOnPage('https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login');
    I.see('LOGIN Panel');
    I.fillField('txtUsername','opensourcecms'); //Can use the ID of an element to locate elements
    I.fillField('txtPassword','wrongpassword');
    I.click('LOGIN'); //Can use the text in the buttons to locate elements
    I.see('Invalid credentials'); //Assertion

});