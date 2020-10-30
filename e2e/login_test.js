Feature('login');

// those tests only work if the database is empty or no user under username "user1" is registred
Scenario('Login user not registered', ({ I }) => {
    I.amOnPage('http://localhost:9080/login');
    I.see('Log In');
    I.fillField('loginUsername','user1');
    I.fillField('loginPassword','password1');
    I.click('bLogin');
    I.see('User not found');

});

Scenario('Register', ({ I }) => {
    I.amOnPage('http://localhost:9080/login');
    I.see('Log In');
    I.fillField('registerUsername','user1');
    I.fillField('registerFirstName','Jean');
    I.fillField('registerLastName','Dupont');
    I.fillField('registerEmail','jean@dupont.ch');
    I.fillField('registerPassword','password1');
    I.click('bRegister');
    I.seeCurrentUrlEquals('http://localhost:9080/login');

});

Scenario('Login user registered', ({ I }) => {
    I.amOnPage('http://localhost:9080/login');
    I.see('Log In');
    I.fillField('loginUsername','user1');
    I.fillField('loginPassword','password1');
    I.click('bLogin');
    I.seeCurrentUrlEquals('http://localhost:9080/questions'); 

});
