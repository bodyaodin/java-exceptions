# Check exceptions in Java
Created exceptions:
* DataException extends RuntimeException;
* ValueNotFoundException extends DataException;
* IncorrectValueException extends DataException;
* ForbiddenSymbolException extends DataException;
* MapContainsSuchElementException extends DataException;
* MapIsEmptyException extends DataException;

Methods for checking exceptions:
* DataManager.addURL();
* DataManager.deleteURL();
* DataManager.getAll();
