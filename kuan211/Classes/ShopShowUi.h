//
//  XGWin32LoginUiLayer.hpp
//  XGDemo
//
//  Created by zhangdaochuan on 15/10/16.
//
//

#ifndef XGWin32LoginUiLayer_hpp
#define XGWin32LoginUiLayer_hpp

#include "cocos2d.h"
#include "cocos-ext.h"
#include "spine/Json.h"
#include "HelloWorldScene.h"
#include <string>
#include "PayHelp.h"

using namespace cocos2d;
using namespace cocos2d::extension;
using namespace cocos2d::ui;
using namespace std;

class ShopShowUi : public UILayer
{
public:
    ShopShowUi();
    ~ShopShowUi();
	CREATE_FUNC(ShopShowUi);
    virtual bool init();
    void PayOkButtonClick(CCObject *sender, TouchEventType type);
	void PayNoButtonClick(CCObject *sender, TouchEventType type);
	void getPayQuantityPriceId();
private:
    UIWidget *_mainwidget;
    UIButton *_LoginOkButton;
	UIButton *_LoginNoButton;
	UITextField *_payQuantity;
	UITextField *_payPrice;
	UITextField *_payId;
};
#endif /* XGWin32LoginUiLayer_hpp */