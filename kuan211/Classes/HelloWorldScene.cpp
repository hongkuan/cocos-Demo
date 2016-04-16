#include "HelloWorldScene.h"

USING_NS_CC;

CCScene* HelloWorld::scene()
{
    // 'scene' is an autorelease object
    CCScene *scene = CCScene::create();
    
    // 'layer' is an autorelease object
    HelloWorld *layer = HelloWorld::create();

    // add layer as a child to scene
    scene->addChild(layer);

    // return the scene
    return scene;
}

// on "init" you need to initialize your instance
bool HelloWorld::init()
{
    //////////////////////////////
    // 1. super init first
    if ( !CCLayer::init() )
    {
        return false;
    }
    
    CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
    CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();

    /////////////////////////////
    // 2. add a menu item with "X" image, which is clicked to quit the program
    //    you may modify it.

    // add a "close" icon to exit the progress. it's an autorelease object
 //   CCMenuItemImage *pCloseItem = CCMenuItemImage::create(
 //                                       "CloseNormal.png",
 //                                       "CloseSelected.png",
 //                                       this,
 //                                       menu_selector(HelloWorld::menuCloseCallback));
 //   
	//pCloseItem->setPosition(ccp(origin.x + visibleSize.width - pCloseItem->getContentSize().width/2 ,
 //                               origin.y + pCloseItem->getContentSize().height/2));

 //   // create menu, it's an autorelease object
 //   CCMenu* pMenu = CCMenu::create(pCloseItem, NULL);
 //   pMenu->setPosition(CCPointZero);
 //   this->addChild(pMenu, 1);

 //   /////////////////////////////
 //   // 3. add your codes below...

 //   // add a label shows "Hello World"
 //   // create and initialize a label
 //   
 //   CCLabelTTF* pLabel = CCLabelTTF::create("Hello World", "Arial", 24);
 //   
 //   // position the label on the center of the screen
 //   pLabel->setPosition(ccp(origin.x + visibleSize.width/2,
 //                           origin.y + visibleSize.height - pLabel->getContentSize().height));

 //   // add the label as a child to this layer
 //   this->addChild(pLabel, 1);

 //   // add "HelloWorld" splash screen"
 //   CCSprite* pSprite = CCSprite::create("HelloWorld.png");

 //   // position the sprite on the center of the screen
 //   pSprite->setPosition(ccp(visibleSize.width/2 + origin.x, visibleSize.height/2 + origin.y));

 //   // add the sprite as a child to this layer
 //   this->addChild(pSprite, 0);

	//加载cocos studio制作的界面
	gui::TouchGroup* rootLayout= gui::TouchGroup::create();
	gui::Layout* loginUi=dynamic_cast<gui::Layout*>(GUIReader::shareReader()->widgetFromJsonFile("DemoLogin/DemoLogin.ExportJson"));
	rootLayout->addWidget(loginUi);
	this->addChild(rootLayout,2);
    
	//获取界面上的关闭按钮并添加监听
	gui::Button *closButton=dynamic_cast<gui::Button*>(loginUi->getChildByName("close_Button"));
	closButton->addTouchEventListener(this,toucheventselector(HelloWorld::touchBotton));

	CCSize size = CCDirector::sharedDirector()->getWinSize();
  
    loginUi->setScaleX(size.width/1024);
	loginUi->setScaleY(size.height/768);

    return true;
}


void HelloWorld::menuCloseCallback(CCObject* pSender)
{
#if (CC_TARGET_PLATFORM == CC_PLATFORM_WINRT) || (CC_TARGET_PLATFORM == CC_PLATFORM_WP8)
	CCMessageBox("You pressed the close button. Windows Store Apps do not implement a close button.","Alert");
#else
    CCDirector::sharedDirector()->end();
#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
    exit(0);
#endif
#endif
}

void HelloWorld::touchBotton(CCObject* pSender,gui::TouchEventType type)
{
	/*switch (type)
	{
	case TOUCH_EVENT_BEGAN:
		CCLOG("TOUCH_EVENT_BEGAN");
		break;
	case TOUCH_EVENT_CANCELED:
		CCLOG("TOUCH_EVENT_CANCELED");
		break;
	case TOUCH_EVENT_ENDED:
		CCLOG("TOUCH_EVENT_ENDED");
		HelloWorld::menuCloseCallback(pSender);
		break;
	case TOUCH_EVENT_MOVED:
		CCLOG("TOUCH_EVENT_MOVED");
		break;
	default:
		break;*/
	if(type == TOUCH_EVENT_ENDED)
	{
		CCLOG("ShopShowUi begin");
		ShopShowUi * shopShowUi = ShopShowUi::create();
		this->addChild(shopShowUi,3);

		CCLOG("ShopShowUi end");
	}
}
