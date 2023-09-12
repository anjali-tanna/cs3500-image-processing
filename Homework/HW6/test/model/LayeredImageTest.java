package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/** Test class for the LayeredImage class. */
public class LayeredImageTest {

  // tests the getLayers() method
  @Test
  public void testGetLayer() {
    Pixel one = new Pixel(33, 55, 123);
    Pixel two = new Pixel(15, 36, 38);
    Pixel three = new Pixel(57, 124, 155);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(one, two, three));
    ArrayList<ArrayList<Pixel>> lolop = new ArrayList<ArrayList<Pixel>>(Arrays.asList(pixels));
    AImage image = new JPEG("OOD Summer 1", lolop);
    AImage imageTwo = new JPEG("blurred_OOD Summer 1", lolop);
    Layer layerOne = new Layer(true, image);
    Layer layerTwo = new Layer(false, imageTwo);
    ArrayList<Layer> list = new ArrayList<Layer>(Arrays.asList(layerOne, layerTwo));
    LayeredImage li = new LayeredImage(list);
    assertEquals(list, li.getLayers());
  }

  // tests the getTopmostVisibleLayer() method
  @Test
  public void testGetTopmostVisibleLayer() {
    Pixel one = new Pixel(33, 55, 123);
    Pixel two = new Pixel(15, 36, 38);
    Pixel three = new Pixel(57, 124, 155);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(one, two, three));
    ArrayList<ArrayList<Pixel>> lolop = new ArrayList<ArrayList<Pixel>>(Arrays.asList(pixels));
    AImage image = new JPEG("OOD Summer 1", lolop);
    AImage imageTwo = new JPEG("blurred_OOD Summer 1", lolop);
    Layer layerOne = new Layer(true, image);
    Layer layerTwo = new Layer(false, imageTwo);
    ArrayList<Layer> list = new ArrayList<Layer>(Arrays.asList(layerOne, layerTwo));
    LayeredImage li = new LayeredImage(list);
    assertEquals(layerOne, li.getTopmostVisibleLayer());
  }

  // tests the addLayer() method
  @Test
  public void testAddLayer() {
    Pixel one = new Pixel(33, 55, 123);
    Pixel two = new Pixel(15, 36, 38);
    Pixel three = new Pixel(57, 124, 155);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(one, two, three));
    ArrayList<ArrayList<Pixel>> lolop = new ArrayList<ArrayList<Pixel>>(Arrays.asList(pixels));
    AImage image = new JPEG("OOD Summer 1", lolop);
    AImage imageTwo = new JPEG("blurred_OOD Summer 1", lolop);
    AImage imageThree = new JPEG("sepia Summer 1", lolop);
    Layer layerOne = new Layer(true, image);
    Layer layerTwo = new Layer(false, imageTwo);
    Layer layerThree = new Layer(true, imageThree);
    ArrayList<Layer> list = new ArrayList<Layer>(Arrays.asList(layerOne, layerTwo));
    ArrayList<Layer> newList = new ArrayList<Layer>(Arrays.asList(layerOne, layerTwo, layerThree));
    LayeredImage li = new LayeredImage(list);
    LayeredImage newLi = new LayeredImage(newList);
    assertEquals(newLi, li.addLayer(true, imageThree));
  }

  // tests the removeLayer() method
  @Test
  public void testRemoveLayer() {
    Pixel one = new Pixel(33, 55, 123);
    Pixel two = new Pixel(15, 36, 38);
    Pixel three = new Pixel(57, 124, 155);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(one, two, three));
    ArrayList<ArrayList<Pixel>> lolop = new ArrayList<ArrayList<Pixel>>(Arrays.asList(pixels));
    AImage image = new JPEG("OOD Summer 1", lolop);
    AImage imageTwo = new JPEG("blurred_OOD Summer 1", lolop);
    AImage imageThree = new JPEG("sepia Summer 1", lolop);
    Layer layerOne = new Layer(true, image);
    Layer layerTwo = new Layer(false, imageTwo);
    Layer layerThree = new Layer(true, imageThree);
    ArrayList<Layer> list = new ArrayList<Layer>(Arrays.asList(layerOne, layerTwo, layerThree));
    ArrayList<Layer> newList = new ArrayList<Layer>(Arrays.asList(layerTwo, layerThree));
    LayeredImage li = new LayeredImage(list);
    LayeredImage newLi = new LayeredImage(newList);
    assertEquals(newLi, li.addLayer(true, image));
  }
}
