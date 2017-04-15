/********************************************************************************
** Form generated from reading UI file 'spreadsheet.ui'
**
** Created by: Qt User Interface Compiler version 5.2.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SPREADSHEET_H
#define UI_SPREADSHEET_H

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

class Ui_spreadsheetClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *spreadsheetClass)
    {
        if (spreadsheetClass->objectName().isEmpty())
            spreadsheetClass->setObjectName(QStringLiteral("spreadsheetClass"));
        spreadsheetClass->resize(600, 400);
        menuBar = new QMenuBar(spreadsheetClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        spreadsheetClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(spreadsheetClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        spreadsheetClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(spreadsheetClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        spreadsheetClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(spreadsheetClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        spreadsheetClass->setStatusBar(statusBar);

        retranslateUi(spreadsheetClass);

        QMetaObject::connectSlotsByName(spreadsheetClass);
    } // setupUi

    void retranslateUi(QMainWindow *spreadsheetClass)
    {
        spreadsheetClass->setWindowTitle(QApplication::translate("spreadsheetClass", "spreadsheet", 0));
    } // retranslateUi

};

namespace Ui {
    class spreadsheetClass: public Ui_spreadsheetClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SPREADSHEET_H
