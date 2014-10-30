package archeologyp2.adt;

import java.util.ArrayList;
import java.util.Collections;

import archeologyp2.shared.finds.Artifact;
import archeologyp2.shared.finds.Charcoal;
import archeologyp2.shared.finds.DecoratedPottery;
import archeologyp2.shared.finds.FerrousMetal;
import archeologyp2.shared.finds.Hearth;
import archeologyp2.shared.finds.Kiln;
import archeologyp2.shared.finds.MetalObject;
import archeologyp2.shared.finds.NonFerrousMetal;
import archeologyp2.shared.finds.Pottery;
import archeologyp2.shared.finds.StoragePottery;
import archeologyp2.shared.finds.SubmergedPottery;

/* Temporary Layout for Report

 -------------------------------------------------------------------
 ||   TYPE   ||   ROW   ||   COLUMN   ||   DATE   ||   PROPERTY   ||
 -------------------------------------------------------------------
 ||          ||         ||            ||          ||              ||
 -------------------------------------------------------------------
 Average Date: -----
 
  */

/**
 * REPORT FOR THE ADT
 * 
 * This class specifies on working on the report and
 * printing it out to the user. 
 * 
 * @author Daniel
 * @author Celine
 */

public class Report {
	
	private ArrayList<Pottery> foundPottery;
	private ArrayList<Charcoal> foundCharcoal;
	private ArrayList<MetalObject> foundMetal;
	
	private String potteryReport;
	private String charcoalReport;
	private String metalReport;
	
	private final String header = "---------------------------------------------------\n" + 
								  "TYPE\t\tROW\tCOLUMN\tDATE\tPROPERTY\n" +
								  "---------------------------------------------------\n";
	
	public Report(){
		potteryReport = "";
		charcoalReport = "";
		metalReport = "";
		
		foundPottery = new ArrayList<>();
		foundCharcoal = new ArrayList<>();
		foundMetal = new ArrayList<>();
	}
	
	public void addFoundItem(Artifact item){
		if(item instanceof Pottery){
			foundPottery.add((Pottery) item);
		} else if (item instanceof Charcoal){
			foundCharcoal.add((Charcoal) item);
		} else if (item instanceof MetalObject){
			foundMetal.add((MetalObject) item);
		}
	}
	
	public void generateReport(){
		Collections.sort(foundPottery);
		Collections.sort(foundCharcoal);
		Collections.sort(foundMetal);
		
		potteryReport += header;
		charcoalReport += header;
		metalReport += header;
		
		for(Pottery pot : foundPottery){
			if(pot instanceof DecoratedPottery){
				potteryReport += "Decorated\t";
				potteryReport += pot.getRow() + "\t";
				potteryReport += pot.getColumn() + "\t";
				potteryReport += pot.getDate() + "\t";
				potteryReport += ((DecoratedPottery) pot).getDescription();
			} else if (pot instanceof StoragePottery){
				potteryReport += "Storage\t\t";
				potteryReport += pot.getRow() + "\t";
				potteryReport += pot.getColumn() + "\t";
				potteryReport += pot.getDate() + "\t";
				potteryReport += ((StoragePottery) pot).getVolume();
			} else if (pot instanceof SubmergedPottery){
				potteryReport += "Submerged\t";
				potteryReport += pot.getRow() + "\t";
				potteryReport += pot.getColumn() + "\t";
				potteryReport += pot.getDate() + "\t";
				potteryReport += ((SubmergedPottery) pot).getDepth();
			}
			potteryReport += "\n";
		}
		
		for(Charcoal charcoal : foundCharcoal){
			if(charcoal instanceof Kiln){
				charcoalReport += "Kiln\t\t";
				charcoalReport += charcoal.getRow() + "\t";
				charcoalReport += charcoal.getColumn() + "\t";
				charcoalReport += charcoal.getDate() + "\t";
				charcoalReport += ((Kiln) charcoal).getRadius();
			} else if (charcoal instanceof Hearth){
				charcoalReport += "Hearth\t\t";
				charcoalReport += charcoal.getRow() + "\t";
				charcoalReport += charcoal.getColumn() + "\t";
				charcoalReport += charcoal.getDate() + "\t";
				charcoalReport += ((Hearth) charcoal).getLength() + "\t";
				charcoalReport += ((Hearth) charcoal).getWidth();
			} 
			charcoalReport += "\n";
		}
		
		for(MetalObject metal : foundMetal){
			if(metal instanceof FerrousMetal){
				metalReport += "Ferrous\t\t";
				metalReport += metal.getRow() + "\t";
				metalReport += metal.getColumn() + "\t";
				metalReport += metal.getDate() + "\t";
				metalReport += ((FerrousMetal) metal).getSignalStrength();
			} else if (metal instanceof NonFerrousMetal){
				metalReport += "Non-Ferrous\t";
				metalReport += metal.getRow() + "\t";
				metalReport += metal.getColumn() + "\t";
				metalReport += metal.getDate() + "\t";
				metalReport += ((NonFerrousMetal) metal).getType();
			}
			metalReport += "\n";
		}
	}
	
	@Override
	public String toString(){
		return potteryReport + "\n\n" + charcoalReport + "\n\n" + metalReport + "\n";
	}
}
