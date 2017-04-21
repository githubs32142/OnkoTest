/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author Admin
 */
public class StringListCellFactory implements Callback<ListView<Factor>, ListCell<Factor>>
 {
 @Override
 public ListCell<Factor> call(ListView<Factor> factListView)
 {
 return new StringListCell();
 }
 
class StringListCell extends ListCell<Factor>
 {
 @Override
 protected void updateItem(Factor fact, boolean b)
 {
 super.updateItem(fact, b);
 
if (fact != null)
 {
 setText(fact.getFactor());
 }
 }
 }
 }
    
