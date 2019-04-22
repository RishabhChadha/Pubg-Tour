import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor'
import { async } from 'q';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('PubGUI');
  });

  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register', () => {
    browser.element(by.id('firstName')).sendKeys('Super User');
    browser.element(by.id('lastName')).sendKeys('Super lastUser');
    browser.element(by.id('userId')).sendKeys('Super User12');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be able to login and navigate to the tournaments list', () => {
    browser.element(by.id('userId')).sendKeys('Super User12');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/tournament/all');
  });

  it('should be able to view favourite matches of pubg', () => {
    browser.element(by.css('.fav-button')).click();
    expect(browser.getCurrentUrl()).toContain('/tournament/favMatch');
    
    }
  );

  it('should be able to add match to favorite matches', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    browser.element(by.css('.tournament-button')).click();
    browser.driver.sleep(2000);
    browser.element(by.css('.matches-button')).click();
    browser.driver.sleep(2000);
    browser.element(by.css('.details-button')).click();
    browser.driver.sleep(2000);
    browser.element(by.css('.add-button')).click();
    const searchItems = element.all(by.css('.movie-thumbnail'));
    expect(browser.getCurrentUrl()).toContain('/tournament/detail/');
  });







});