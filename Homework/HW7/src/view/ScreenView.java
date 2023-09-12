package view;

import colortransformations.Grayscale;
import colortransformations.Sepia;
import controller.ScreenImageController;
import filtertransformations.Blur;
import filtertransformations.Sharpen;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * ScreenView class shows the display of the Image editing software.
 */
public class ScreenView extends JFrame implements ActionListener, ImageView {

  protected JPanel imagePanel;
  public JLabel imageLabel;
  public JScrollPane imageScrollPane;
  private final JMenuBar menu = new JMenuBar();
  protected ScreenImageController controller;


  /**
   * Constructor for the ScreenView class.
   */
  public ScreenView() {
    super();
    controller = new ScreenImageController();
    setSize(new Dimension(1500, 1500));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    menu.setBorder(BorderFactory.createTitledBorder("Menu"));
    add(menu, BorderLayout.PAGE_START);
    imagePanel = new JPanel();

    buildImagePanel();
    buildFileSubmenu();
    buildApplySubmenu();
    buildLayerSubmenu();

    pack();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "jpg save": {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(ScreenView.this);
        if (option == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          String folder = ("Folder Selected: " + file.getAbsolutePath());

        }
      }
      break;
      case "Load im": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PNG Images", "jpg", "png");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(ScreenView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          addImage(f.getAbsolutePath());
          controller.setFilename(f.getAbsolutePath());
        }
      }
      break;
      case "Make Layered": {
        controller.newLayeredImage();
      }
      break;
      case "Load layers": {
        final JFileChooser cchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Text files", "txt");
        cchooser.setFileFilter(filter);
        int retvalue = cchooser.showOpenDialog(ScreenView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = cchooser.getSelectedFile();
          addImage(controller.getTopmostFilename(f.getAbsolutePath()));
        }
      }
      break;
      case "Commands": {
        final JFileChooser cchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Text files", "txt");
        cchooser.setFileFilter(filter);
        int retvalue = cchooser.showOpenDialog(ScreenView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = cchooser.getSelectedFile();
        }
      }
      break;
      case "Grayscale": {
        controller.colorImage(new Grayscale());
      }
      break;
      case "Sepia": {
        controller.colorImage(new Sepia());
      }
      break;
      case "Sharpen": {
        controller.filterImage(new Sharpen());
      }
      break;
      case "Blur": {
        controller.filterImage(new Blur());
      }
      break;
      case "Remove": {
        controller.remove(JOptionPane.showInputDialog("Please enter layer number to remove"));
      }
      break;
      case "Add": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG, & PPM Images", "jpg", "png", "ppm");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(ScreenView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          controller.add(f.getAbsolutePath());
        }
      }
      break;
      default:
        // no action required
    }
  }

  private void buildImagePanel() {
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image Viewer:"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    add(imagePanel, BorderLayout.CENTER);
    // Image View:
    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
  }

  private void buildFileSubmenu() {
    JMenu file = new JMenu("File");
    JMenuItem save = new JMenuItem("Save");
    file.add(save);

    JMenu saveAs = new JMenu("Save As");
    JMenuItem jpg = new JMenuItem(".jpg");
    jpg.setActionCommand("jpg save");
    jpg.addActionListener(this);
    saveAs.add(jpg);
    JMenuItem png = new JMenuItem(".png");
    png.setActionCommand("png save");
    png.addActionListener(this);
    saveAs.add(png);
    JMenuItem ppm = new JMenuItem(".ppm");
    ppm.setActionCommand("ppm save");
    ppm.addActionListener(this);
    saveAs.add(ppm);
    file.add(saveAs);

    JMenu load = new JMenu("Load");
    JMenuItem im = new JMenuItem("Image");
    im.setActionCommand("Load im");
    im.addActionListener(this);
    load.add(im);
    JMenuItem cf = new JMenuItem("Command File");
    cf.setActionCommand("Commands");
    cf.addActionListener(this);
    load.add(cf);
    JMenuItem li = new JMenuItem("Layered Image");
    li.setActionCommand("Load layers");
    li.addActionListener(this);
    load.add(li);
    file.add(load);

    JMenuItem makeLayered = new JMenuItem("Make Layered");
    makeLayered.setActionCommand("Make Layered");
    makeLayered.addActionListener(this);
    file.add(makeLayered);
    menu.add(file);
  }

  private void buildApplySubmenu() {
    JMenu apply = new JMenu("Apply...");
    JMenuItem grayscale = new JMenuItem("Grayscale");
    grayscale.setActionCommand("Grayscale");
    grayscale.addActionListener(this);
    apply.add(grayscale);
    JMenuItem sepia = new JMenuItem("Sepia");
    sepia.setActionCommand("Sepia");
    sepia.addActionListener(this);
    apply.add(sepia);
    JMenuItem blur = new JMenuItem("Blur");
    blur.setActionCommand("Blur");
    blur.addActionListener(this);
    apply.add(blur);
    JMenuItem sharpen = new JMenuItem("Sharpen");
    sharpen.setActionCommand("Sharpen");
    sharpen.addActionListener(this);
    apply.add(sharpen);
    menu.add(apply);
  }

  private void buildLayerSubmenu() {
    JMenu layers = new JMenu("Layers");
    JMenuItem remove = new JMenuItem("Remove Layer");
    remove.setActionCommand("Remove");
    remove.addActionListener(this);
    layers.add(remove);
    JMenuItem add = new JMenuItem("Add Layer");
    add.setActionCommand("Add");
    add.addActionListener(this);
    layers.add(add);
    menu.add(layers);
  }

  private void addImage(String filename) {
    imageLabel.setIcon(new ImageIcon(filename));
    imagePanel.add(imageScrollPane);
  }

}

