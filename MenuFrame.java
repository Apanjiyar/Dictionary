import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class MenuFrame extends JFrame {

    public MenuFrame() throws Exception {
        super("Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();

        JMenu mnuDictionary = new JMenu("Dictionary");
        mb.add(mnuDictionary);

        JMenuItem option = new JMenuItem("Add Word...");
        option.setIcon(getImage("add.gif"));
        option.setAccelerator(KeyStroke.getKeyStroke("F5"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.this.addWord();
            }
        });
        option = new JMenuItem("Delete Word...");
        option.setIcon(getImage("Delete.gif"));
        option.setAccelerator(KeyStroke.getKeyStroke("F6"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.this.deleteWord();
            }
        });
        mnuDictionary.addSeparator();

        option = new JMenuItem("Search Word...");
        option.setIcon(getImage("search.gif"));
        option.setAccelerator(KeyStroke.getKeyStroke("F7"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.this.searchWord();
            }
        });
        option = new JMenuItem("List Words");
        option.setIcon(getImage("list.gif"));
        option.setAccelerator(KeyStroke.getKeyStroke("F8"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.this.listWords();
            }
        });
        mnuDictionary.addSeparator();

        option = new JMenuItem("Exit");
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.this.exit();
            }
        });
        addStorageMenu(mb);
        addToolbar();
        setJMenuBar(mb);

        Dictionary.loadFromDisk();
    }
    public void exit() {
        if (Dictionary.isModified()) {
            int option = JOptionPane.showConfirmDialog(this, "You have some pending changes. Do you want to write them to disk and then exit?", "Save", 1, 3);
            if (option == 0) {
                Dictionary.saveToDisk();
                System.exit(0);
            } else if (option == 1) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
    public ImageIcon getImage(String filename) {
        return new ImageIcon(getClass().getResource("/images/" + filename));
    }
    public void centerToParent(JFrame parent, JFrame child) {
        Dimension pd = parent.getSize();
        Dimension cd = child.getSize();
        int x = (int)(pd.getWidth() - cd.getWidth()) / 2;
        int y = (int)(pd.getHeight() - cd.getHeight()) / 2;
        child.setLocation(x, y);
    }
    public void addWord() {
        AddWord w = new AddWord();
        centerToParent(this, w);
        w.setVisible(true);
    }
    public void deleteWord() {
        DeleteWord w = new DeleteWord();
        centerToParent(this, w);
        w.setVisible(true);
    }
    public void searchWord() {
        SearchWord w = new SearchWord();
        centerToParent(this, w);
        w.setVisible(true);
    }
    public void listWords() {
        ListWords w = new ListWords();
        centerToParent(this, w);
        w.setVisible(true);
        centerToParent(this, w);
    }
    public void addToolbar() throws Exception {
        JToolBar tb = new JToolBar();
        JButton b = new JButton(getImage("add.gif"));
        b.setPreferredSize(new Dimension(32, 32));
        tb.add(b);
        b.setToolTipText("Add word");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.this.addWord();
            }
        });
        b = new JButton(getImage("delete.gif"));
        b.setPreferredSize(new Dimension(32, 32));
        tb.add(b);
        b.setToolTipText("Delete word");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.this.deleteWord();
            }
        });
        b = new JButton(getImage("search.gif"));
        b.setPreferredSize(new Dimension(32, 32));
        tb.add(b);
        b.setToolTipText("Search word");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.this.searchWord();
            }
        });
        b = new JButton(getImage("list.gif"));
        tb.add(b);
        b.setToolTipText("List Words");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFrame.this.listWords();
            }
        });
        tb.addSeparator();
        b = new JButton(getImage("Save.gif"));
        tb.add(b);
        b.setToolTipText("Save Dictionary To Disk");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dictionary.saveToDisk();
            }
        });
        b = new JButton(getImage("load.gif"));
        tb.add(b);
        b.setToolTipText("Load Dictionary From Disk");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dictionary.loadFromDisk();
            }
        });
        getContentPane().add(tb, "North");
    }
    public void addStorageMenu(JMenuBar mb) {
        JMenu mnuStorage = new JMenu("Storage");
        JMenuItem option = new JMenuItem("Save Dictionary");
        option.setIcon(getImage("save.gif"));
        option.setAccelerator(KeyStroke.getKeyStroke("F2"));
        mnuStorage.add(option);
        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean result = Dictionary.saveToDisk();
                if (result) {
                    JOptionPane.showMessageDialog(MenuFrame.this, "Svaed Dictionary Successfully", "FeedBack", 1);
                } else {
                    JOptionPane.showMessageDialog(MenuFrame.this, "Could not save Dictionary Successfully ! Error-->" + Dictionary.getMessage(), "Feedback", 1);
                }
            }
        });
        option = new JMenuItem("Load Dictionary");
        option.setIcon(getImage("load.gif"));

        option.setAccelerator(KeyStroke.getKeyStroke("F3"));
        mnuStorage.add(option);
        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean result = Dictionary.loadFromDisk();
                if (result) {
                    JOptionPane.showMessageDialog(MenuFrame.this, "Loaded Dictionary SuccessFully!", "FeedBack", 1);
                } else {
                    JOptionPane.showMessageDialog(MenuFrame.this, "Could Not Load Dictionary Successfully !Error--->" + Dictionary.getMessage(), "FeedBack", 1);
                }
            }
        });
        mb.add(mnuStorage);
    }
    public static void main(String[] args) {
        try {
            MenuFrame f = new MenuFrame();
            f.setVisible(true);
            f.setExtendedState(6);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}