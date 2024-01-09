package inputValidate;

import java.io.File;

public class InvalidFileFormatException extends Exception {
    public InvalidFileFormatException(String message) {
        super(message);
    }

    public static void checkFileFormat(File file) throws InvalidFileFormatException {
        String fileName = file.getName().toLowerCase();

        if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
            throw new InvalidFileFormatException("Niewłaściwy format pliku. Akceptowalne formaty to PNG i JPG.");
        }
    }
}

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JFileChooser fileChooser = new JFileChooser();
//        File selectedFile = null;
//        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//        int result = fileChooser.showOpenDialog(frame);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            selectedFile = fileChooser.getSelectedFile();
//            try {
//                InvalidFileFormatException.checkFileFormat(selectedFile);
//            } catch (InvalidFileFormatException ex) {
//                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Błąd formatu pliku", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }