Feature('Projet_1');

Scenario('test de question', ({ I }) => {
    I.amOnPage('http://localhost:9080/Projet_1/questionread');
    I.see('Question');
  })
