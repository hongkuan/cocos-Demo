#include "ShopShowUi.h"

ShopShowUi::ShopShowUi()
{
}
ShopShowUi::~ShopShowUi()
{
}

bool ShopShowUi::init()
{
    if(!UILayer::init())
    {
        return false;
    }
    
    _mainwidget = GUIReader::shareReader()->widgetFromJsonFile("ShopShowUi/ShopShowUi.json");
    this->addWidget(_mainwidget);
    
    _LoginOkButton = static_cast<UIButton *>(_mainwidget->getChildByName("payOkBt"));
	_LoginOkButton->addTouchEventListener(this, toucheventselector(ShopShowUi::PayOkButtonClick));
    
	_LoginNoButton = static_cast<UIButton *>(_mainwidget->getChildByName("payNoBt"));
    _LoginNoButton->addTouchEventListener(this, toucheventselector(ShopShowUi::PayNoButtonClick));

	 _payQuantity = static_cast<UITextField *>(_mainwidget->getChildByName("payQuantity"));
	
	_payPrice =  static_cast<UITextField *>(_mainwidget->getChildByName("payPrice"));

	_payId =  static_cast<UITextField *>(_mainwidget->getChildByName("payId"));

    _payQuantity->setText("1");
	//_payQuantity->seti
	_payPrice->setText("1");

	_payId->setText("1");

    CCSize size = CCDirector::sharedDirector()->getWinSize();
  
    _mainwidget->setScaleX(size.width/480/2);
	_mainwidget->setScaleY(size.height/320/2);


    return true;
}

void ShopShowUi::PayOkButtonClick(cocos2d::CCObject *sender, TouchEventType type)
{
    if(type==TOUCH_EVENT_ENDED)
    {
        CCLOG("this is LoginOkButtonClick");
        const char *payQuantity = _payQuantity->getStringValue();
		const char *payPrice = _payPrice->getStringValue();
		const char *payId = _payId->getStringValue();
		
		if(strcmp(payQuantity, "") == 0 || strcmp(payPrice, "") == 0 ||strcmp(payId, "") == 0)
		{
			string info = "please input ";
			boolean falg = false;
			if(strcmp(payQuantity, "") == 0 )
			{
				CCLOG("user pay Quantity is empty.");
				info += "user pay Quantity";
				falg = true;
			}
			if(strcmp(payPrice, "") == 0 )
			{
				CCLOG("payPrice is empty.");
				if(falg)
				{
					info += " and ";
				}
				info += "payPrice";
				falg = true;
			}
			if(strcmp(payId, "") == 0 )
			{
				CCLOG("payId is empty.");
				if(falg)
				{
					info += " and ";
				}
				info += "payId";
			}

			info +=".";
			 
			CCMessageBox(info.c_str(),"Error");
		}else
		{
			PayHelp::getInstance().setPayQuantity(payQuantity);
			PayHelp::getInstance().setPayPrice(payPrice);
			PayHelp::getInstance().setPayId(payId);
			this->removeFromParentAndCleanup(true);
		}
    }
}

void ShopShowUi::PayNoButtonClick(CCObject *sender, TouchEventType type)
{
	if(type== TOUCH_EVENT_ENDED)
	{
		this->removeFromParentAndCleanup(true);
	}
}
