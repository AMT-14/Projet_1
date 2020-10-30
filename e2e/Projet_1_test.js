Feature('Projet_1');

Scenario('Login user with valid credentials', ({ I }) => {
    I.amOnPage('http://localhost:9080/login');
    I.see('Log In');
   

});

Scenario('Login user with invalid credentials ', ({ I }) => {
    I.amOnPage('https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login');
    I.see('LOGIN Panel');
    I.fillField('txtUsername','opensourcecms'); //Can use the ID of an element to locate elements
    I.fillField('txtPassword','wrongpassword');
    I.click('LOGIN'); //Can use the text in the buttons to locate elements
    I.see('Invalid credentials'); //Assertion

});
Scenario('test de question', ({ I }) => {
    I.amOnPage('http://localhost:9080/Projet_1/questionread');
    I.see('Question');
  })
