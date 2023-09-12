package model;

import colortransformations.AColorTransformation;
import filtertransformations.AFilter;
import java.util.ArrayList;

/**
 * Abstract class that represents a LayeredImage which implements the Image interface. Includes the
 * list of layers in the image.
 */
public class LayeredImage implements Image {

  protected ArrayList<Layer> layers;

  /**
   * Constructor for a LayeredImage, which includes the list of Layers.
   *
   * @throws IllegalArgumentException if the list of layers is null, the layers are not the same
   *     size, or the layers do not match in FileTypes
   */
  public LayeredImage(ArrayList<Layer> layers) {
    if (layers == null) {
      throw new IllegalArgumentException("Layers cannot be null");
    }
    if (!checkLayersSize()) {
      throw new IllegalArgumentException("All layers must be the same size");
    }
    this.layers = new ArrayList<Layer>();
  }

  /**
   * Checks the size of the Layer and if it is valid.
   *
   * @return boolean if the layer size is valid
   */
  private boolean checkLayersSize() {
    boolean valid = true;
    if (this.layers.size() == 0) {
      return true;
    } else {
      int height = this.layers.get(0).image.getPixels().size();
      int length = this.layers.get(0).image.getPixels().get(0).size();
      for (int i = 0; i < this.layers.size(); i++) {
        if (this.layers.get(i).image.getPixels().size() != height
            || this.layers.get(i).image.getPixels().get(0).size() != length) {
          return false;
        } else {
          valid = true;
        }
      }
    }
    return valid;
  }

  /**
   * Gets the list of Layers.
   *
   * @return Array List of layers
   */
  public ArrayList<Layer> getLayers() {
    ArrayList<Layer> newLayers = new ArrayList<Layer>();
    for (int i = 0; i < this.layers.size(); i++) {
      newLayers.add(this.layers.get(i));
    }
    return newLayers;
  }

  /**
   * Gets the topmost visible layer of the image.
   *
   * @return AImage which is the top layer
   */
  public AImage getTopmostVisibleLayer() {
    for (int i = 0; i < this.getLayers().size(); i++) {
      if (this.getLayers().get(i).isVisible) {
        return this.getLayers().get(i).image;
      }
    }
    throw new IllegalArgumentException("No visible layers");
  }

  /**
   * Applies the color transformations to the layer.
   *
   * @param layer the layer being transformed
   * @param ct the type of transformation (sepia or grayscale)
   */
  public void applyTransformationTo(int layer, AColorTransformation ct) {
    if (layer > layers.size() - 1 || layer < 0) {
      throw new IllegalArgumentException("Layer number not found");
    }
    if (ct == null) {
      throw new IllegalArgumentException("Color transformation cannot be null");
    }
    Layer layerWanted = this.layers.get(layer);
    this.layers.set(
        layer,
        new Layer(
            layerWanted.isVisible,
            layerWanted.image.applyColorTransformation(ct, layerWanted.image.getFilename())));
  }

  /**
   * Applies the filter to the layer.
   *
   * @param layer the layer being filtered
   * @param f the type of filter (blur or sharpen)
   */
  public void applyFilterTo(int layer, AFilter f) {
    if (layer > layers.size() - 1 || layer < 0) {
      throw new IllegalArgumentException("Layer number not found");
    }
    if (f == null) {
      throw new IllegalArgumentException("Filter cannot be null");
    }
    Layer layerWanted = this.layers.get(layer);
    this.layers.set(
        layer,
        new Layer(
            layerWanted.isVisible,
            layerWanted.image.applyFilter(f, f.matrix[0].length, layerWanted.image.getFilename())));
  }

  /**
   * Adds a layer to the LayeredImage.
   *
   * @param isVisible whether or not the layer is visible
   * @param image the image with the layer being added to
   */
  public void addLayer(boolean isVisible, AImage image) {
    this.layers.add(new Layer(isVisible, image));
  }

  /**
   * Removes a layer from the LayeredImage.
   *
   * @param layer index of the layer being removed
   */
  public void removeLayer(int layer) {
    this.layers.remove(layer);
  }
}
