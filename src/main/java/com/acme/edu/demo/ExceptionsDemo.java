package com.acme.edu.demo;

import java.io.*;

//e.pST | j.u.l | SLFJ (logback)
public class ExceptionsDemo {
    public static void main(String[] args) {
        try (final Controller controller = new Controller()) {
            controller.log();
        } catch (LogOperationException e) {
            e.printStackTrace();
        } catch (Throwable all) {
            all.printStackTrace();
            //userController.sendGenericError
        }
    }
}

class Controller implements AutoCloseable  {
    private PuperSaver saver = new PuperSaver();
    private PuperDecorator decorator = new PuperDecorator();

    public void log() throws LogOperationException {
        try {
            saver.save(decorator.decorate());
        } catch (IOException e) {
            throw new LogOperationException("Super useful message", e); //re-throw
        }
    }

    @Override
    public void close() {
        throw new RuntimeException("close");
    }
}

class PuperSaver {
    public void save(String decoratedMessage) throws IOException {
        throw new IOException();
    }
}

class PuperDecorator {
    public String decorate() {
        return null;
    }
}

class LogOperationException extends Exception {
    public LogOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogOperationException(Throwable cause) {
        super(cause);
    }
}