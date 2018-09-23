# FieldBinder Add-on for Vaadin 8

FieldBinder is a little sibling of Binder for special case of single
field bindings. FieldBinder enables to use same Converters, Validators and
similar API to Binder with single field binding.

FieldBinder connects one Field component with value with one direction binding. 

A binder is a <i>binding</i>, representing the mapping of a single field,
through converters and validators, and acts as a buffer for bound value. 

A binder instance can be bound to a single value and field instance at a time,
but can be rebound as needed.

This add-on does not have client side implementation, widgetset compilation
is not needed.

### Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/fieldbinder

## Building and running demo

git clone <url of the FieldBinder repository>
mvn clean install
cd demo
mvn jetty:run

To see the demo, navigate to http://localhost:8080/

## Development with Eclipse IDE

For further development of this add-on, the following tool-chain is recommended:
- Eclipse IDE
- m2e wtp plug-in (install it from Eclipse Marketplace)
- Vaadin Eclipse plug-in (install it from Eclipse Marketplace)
- JRebel Eclipse plug-in (install it from Eclipse Marketplace)
- Chrome browser

### Importing project

Choose File > Import... > Existing Maven Projects

Note that Eclipse may give "Plugin execution not covered by lifecycle configuration" errors for pom.xml. Use "Permanently mark goal resources in pom.xml as ignored in Eclipse build" quick-fix to mark these errors as permanently ignored in your project. Do not worry, the project still works fine. 

### Debugging server-side

If you have not already compiled the widgetset, do it now by running vaadin:install Maven target for fieldbinder-root project.

If you have a JRebel license, it makes on the fly code changes faster. Just add JRebel nature to your fieldbinder-demo project by clicking project with right mouse button and choosing JRebel > Add JRebel Nature

To debug project and make code modifications on the fly in the server-side, right-click the fieldbinder-demo project and choose Debug As > Debug on Server. Navigate to http://localhost:8080/fieldbinder-demo/ to see the application.

## Release notes

### Version 0.1.0
- Initial release

## Roadmap

This component is developed as a hobby with no public roadmap or any guarantees of upcoming releases. All feedback is welcome.  

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

FieldBinder is written by Tatu Lund

# Developer Guide

## Getting started

Add-on has JavaDocs

FieldBinder JavaDoc is available online at <...>
