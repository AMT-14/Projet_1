Feature('question');

// you must have called the tests in login_test.js before those ones cause it creates the user we use to go thrue the app
Scenario('Login and write a question', ({ I }) => {
    I.amOnPage('http://localhost:9080/login');
    I.see('Log In');
    I.fillField('loginUsername','user1');
    I.fillField('loginPassword','password1');
    I.click('bLogin');
    I.seeCurrentUrlEquals('http://localhost:9080/questions');
    I.fillField('text','question 1 for test purpose');
    I.click('bSubmitQuestion');
    I.see('question 1 for test purpose');
});
