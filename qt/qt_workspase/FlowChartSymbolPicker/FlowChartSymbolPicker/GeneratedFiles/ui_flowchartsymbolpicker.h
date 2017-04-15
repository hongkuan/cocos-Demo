/********************************************************************************
** Form generated from reading UI file 'flowchartsymbolpicker.ui'
**
** Created by: Qt User Interface Compiler version 5.2.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_FLOWCHARTSYMBOLPICKER_H
#define UI_FLOWCHARTSYMBOLPICKER_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_FlowChartSymbolPickerClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *FlowChartSymbolPickerClass)
    {
        if (FlowChartSymbolPickerClass->objectName().isEmpty())
            FlowChartSymbolPickerClass->setObjectName(QStringLiteral("FlowChartSymbolPickerClass"));
        FlowChartSymbolPickerClass->resize(600, 400);
        menuBar = new QMenuBar(FlowChartSymbolPickerClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        FlowChartSymbolPickerClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(FlowChartSymbolPickerClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        FlowChartSymbolPickerClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(FlowChartSymbolPickerClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        FlowChartSymbolPickerClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(FlowChartSymbolPickerClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        FlowChartSymbolPickerClass->setStatusBar(statusBar);

        retranslateUi(FlowChartSymbolPickerClass);

        QMetaObject::connectSlotsByName(FlowChartSymbolPickerClass);
    } // setupUi

    void retranslateUi(QMainWindow *FlowChartSymbolPickerClass)
    {
        FlowChartSymbolPickerClass->setWindowTitle(QApplication::translate("FlowChartSymbolPickerClass", "FlowChartSymbolPicker", 0));
    } // retranslateUi

};

namespace Ui {
    class FlowChartSymbolPickerClass: public Ui_FlowChartSymbolPickerClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_FLOWCHARTSYMBOLPICKER_H
