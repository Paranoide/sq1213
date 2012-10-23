package sq1213.blatt01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Paranoide
 */
public class MyTextEditor extends JFrame implements ActionListener,
                                                    DocumentListener
{
    private BorderLayout mainLayout;
    
    private JMenuBar menu;
    private JMenu fileMenu;
    private JMenuItem newMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem quitMenuItem;
    
    private JMenu toolsMenu;
    private JMenuItem countWordsMenuItem;
    private JMenuItem countCharactersMenuItem;
    private JMenuItem sortLinesMenuItem;
    
    private JTextArea input;
    
    private File saveFile = null;
    private boolean edited = false;
    
    public MyTextEditor(String name)
    {
        super(name);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.mainLayout = new BorderLayout();
        this.setLayout(this.mainLayout);
        
        this.input = new JTextArea();
        this.add(this.input, BorderLayout.CENTER);
        
        
        // The Menu
        this.menu = new JMenuBar();
        
        this.fileMenu = new JMenu("File");
        this.newMenuItem = new JMenuItem("New file");
        this.fileMenu.add(this.newMenuItem);
        this.loadMenuItem = new JMenuItem("Load file");
        this.fileMenu.add(this.loadMenuItem);
        this.saveMenuItem = new JMenuItem("Save file");
        this.fileMenu.add(this.saveMenuItem);
        this.saveAsMenuItem = new JMenuItem("Save file as...");
        this.fileMenu.add(this.saveAsMenuItem);
        this.quitMenuItem = new JMenuItem("Quit");
        this.fileMenu.add(this.quitMenuItem);
        this.menu.add(this.fileMenu);
        
        this.toolsMenu = new JMenu("Tools");
        this.countWordsMenuItem = new JMenuItem("Count words");
        this.toolsMenu.add(this.countWordsMenuItem);
        this.countCharactersMenuItem = new JMenuItem("Count characters");
        this.toolsMenu.add(this.countCharactersMenuItem);
        this.sortLinesMenuItem = new JMenuItem("Sort lines");
        this.toolsMenu.add(this.sortLinesMenuItem);
        this.menu.add(this.toolsMenu);
        
        this.setJMenuBar(this.menu);
        
        
        this.setSize(600, 600);
        
        this.setListeners();
        
    }
    
        
    public void newDocument()
    {
        if (this.checkEdited())
        {
            this.saveFile = null;
            this.input.setText("");
        }
    }
    
    public void loadDocument()
    {
        if (this.checkEdited())
        {
            File file = this.getLoadFile();
            if (file != null)
            {
                this.saveFile = file;
                try (FileReader fr = new FileReader(file))
                {
                    this.input.read(fr, file);
                    this.input.getDocument().addDocumentListener(this);
                }
                catch (IOException ioe)
                {
                    System.err.println(ioe.getMessage());
                }
            }
        }
        
    }
    
    public boolean saveDocument()
    {
        File file = this.saveFile;
        return this.saveDocumentAs(file);
    }
    
    public boolean saveDocumentAs(File file)
    {
        boolean saved = false;
        
        if (file == null)
        {
            file = this.getSaveFile();
        }
        
        if (file != null)
        {
            this.saveFile = file;
            try (FileWriter fw = new FileWriter(file))
            {
                this.input.write(fw);
            }
            catch (IOException ioe)
            {
                System.err.println(ioe.getMessage());
            }
            saved = true;
        }
        return saved;
    }
    
    public int countWords()
    {
        int count = this.input.getText().split("[\\s]").length;
        return count;
    }
    
    public int countCharacters()
    {
        int count = this.input.getText().length();
        return count;
    }
    
    public void sortLines()
    {
        String[] lines = this.input.getText().split("\n");
        System.out.println(java.util.Arrays.toString(lines));
        java.util.Arrays.sort(lines);
        this.input.setText("");
        for (int t = 0; t < lines.length; t++)
        {
            this.input.append(lines[t] + "\n");
        }
    }
    
    // -------------------------------
    // LISTENER METHODS --------------
    // -------------------------------
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == this.newMenuItem)
        {
            this.newDocument();
        }
        else if (ae.getSource() == this.loadMenuItem)
        {
            this.loadDocument();
        }
        else if (ae.getSource() == this.saveMenuItem)
        {
            this.saveDocument();
        }
        else if (ae.getSource() == this.saveAsMenuItem)
        {
            this.saveDocumentAs(null);
        }
        else if (ae.getSource() == this.quitMenuItem)
        {
            if (this.checkEdited())
            {
                System.exit(0);
            }
        }
        else if (ae.getSource() == this.countWordsMenuItem)
        {
            this.showWordCount();
        }
        else if (ae.getSource() == this.countCharactersMenuItem)
        {
            this.showCharacterCount();
        }
        else if (ae.getSource() == this.sortLinesMenuItem)
        {
            this.sortLines();
        }
    }
    
    @Override
    public void insertUpdate(DocumentEvent e)
    {
        edited = true;
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        edited = true;
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
        edited = true;
    }
    
    // -------------------------------
    // PRIVATE METHODS ---------------
    // -------------------------------
    
    private void showWordCount()
    {
        int count = this.countWords();
        String msg = "There are " + count + " words.";
        JOptionPane.showMessageDialog(this, msg, "Word count", JOptionPane.OK_OPTION);
    }
    
    private void showCharacterCount()
    {
        int count = this.countCharacters();
        String msg = "There are " + count + " characters.";
        JOptionPane.showMessageDialog(this, msg, "Character count", JOptionPane.OK_OPTION);
    }
    
    private File getSaveFile()
    {
        String dir = System.getProperty("user.dir");
        if (this.saveFile != null)
        {
            dir = this.saveFile.getParentFile().getAbsolutePath();
        }
        
        JFileChooser jfc = new JFileChooser(System.getProperty(dir));
        int choice = jfc.showSaveDialog(this);
        File file = null;
        if (choice == JFileChooser.APPROVE_OPTION)
        {
            file = jfc.getSelectedFile();
        }
        return file;
    }
    
    private File getLoadFile()
    {
        String dir = System.getProperty("user.dir");
        if (this.saveFile != null)
        {
            dir = this.saveFile.getParentFile().getAbsolutePath();
        }
        
        JFileChooser jfc = new JFileChooser(System.getProperty(dir));
        int choice = jfc.showOpenDialog(this);
        File file = null;
        if (choice == JFileChooser.APPROVE_OPTION)
        {
            file = jfc.getSelectedFile();
        }
        return file;
    }
    
    private void setListeners()
    {
        WindowListener wl = new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if (checkEdited())
                {
                    System.exit(0);
                }
            }
        };
        this.addWindowListener(wl);
        
        this.input.getDocument().addDocumentListener(this);
        
        this.newMenuItem.addActionListener(this);
        this.loadMenuItem.addActionListener(this);
        this.saveMenuItem.addActionListener(this);
        this.saveAsMenuItem.addActionListener(this);
        this.quitMenuItem.addActionListener(this);
        this.countWordsMenuItem.addActionListener(this);
        this.countCharactersMenuItem.addActionListener(this);
        this.sortLinesMenuItem.addActionListener(this);
    }

    private boolean checkEdited()
    {
        boolean notCanceled = true;
        String msg, title;
        int type;
        final int YES = JOptionPane.YES_OPTION;
        final int NO = JOptionPane.NO_OPTION;
        final int CANCEL = JOptionPane.CANCEL_OPTION;
        
        if (this.edited)
        {
            title = "Unsaved changes";
            msg = "You have unsaved changed. Do you want to save before quit?";
            type = JOptionPane.YES_NO_CANCEL_OPTION;
            
            int answer = JOptionPane.showConfirmDialog(this, msg, title, type);
            
            if (answer == YES)
            {
                if (!this.saveDocument())
                {
                    notCanceled = false;
                }
            }
            else if (answer == NO)
            {
                // NOTHING
            }
            else if (answer == CANCEL)
            {
                notCanceled = false;
            }
        }
        
        return notCanceled;
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        MyTextEditor mte = new MyTextEditor("Editor");
        mte.setVisible(true);
    }
}
