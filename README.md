<h1>JavaProject and Other Dates</h1>

<ul>
<li><strike>Phase One due September 29th.</li>
<li><strike>CSE1325 Midterm on October 20th.</li>
<li><strike>Phase Two due November 3rd.</li>
<li>Phase Three due November 24</li>
<li>CSE1325 Final</li>
</ul>


<h1>To Do for Phase Three</h1>
<ul>
<li>1. Subclasses for pottery </li>
  <ul>
  <li>1.1.1. Storage</li>
    <ul>
    <li>1.1.1.1. double volumn //variable for volume of the pottery</li>
    </ul>
  <li>1.1.2. Decorated</li>
    <ul>
    <li>1.1.2.1. String description //one word description to describe the pottery, ie "sacrifice" "moon" "sun"</li>
    </ul>
  <li>1.1.3. Submerged</li>
    <ul>
    <li>1.1.3.1. int depth //depth in meters</li>
    </ul>
  </ul>
<li>2. Subclasses for charcoal</li>
  <ul>
    <li>2.1. Kiln</li>
      <ul>
      <li>2.1.1. variable representing the radius in meters
      </ul>
    <li>2.2. Hearth</li>
      <ul>
      <li> 2.2.1. two data members to describe length and width in meters
      </ul>
  </ul>
<li>3. Subclasses for metalwork</li>
  <ul>
  <li>3.1. Ferrous</li>
      <ul>
      <li> 3.1.1. int strength //indicator; the stronger the indicator, the larger the iron object
      </ul>
  <li>3.2. Nonferrous</li>
      <ul>
      <li> 3.2.1. String type //indicates what the different metals are (copper, bronze, or gold)
      </ul>
  </ul>
<li>4. Change the load and save methods to the new file standard</li>
<li>5. Metalwork parent class; must be abstract</li>
    <ul>
    <li> 5.1. respondToMetalDetector() - returns a specific integer
      <ul>
      <li> 5.1.1. At least one ferrous find: return value of 2
      <li> 5.1.2. At least one non-ferrous find: return value of 4
      <li> 5.1.3. No metal finds: return 0
      <li> 5.1.4. Both: return 6
      </ul>
    <li> -- Straight from the project description: "...make the metalwork parent class an abstract class, because each of them will have an abstract method that must be overridden."
    </ul>
<li>6. Static behavior -- one gold item on the map</li>
  <ul>
  <li>6.1. If there's a new gold object, the old one should either be overwritten or removed</li>
  </ul>
<li>7. Map coordinates can be marked heritage</li>
  <ul>
  <li>7.1. Throw custom exception if user tries to dig</li>
  <li> -- Stright from the project description: "Add a new function to your map to assign a new property to all map squares."
  </ul>
<li>8. GUI</li>
  <ul>
  <li>8.1. Figure out threading</li>
  <li>8.2. Three ActionListeners</li>
    <ul>
    <li>8.2.1. One Listener for Shared (default)</li>
      <ul>8.2.1.1. This one is going to be the "layout" for the other two files - very generic; will copy and paste the other two from this and make them specific toward their respective duties.
      </ul>
    <li>8.2.2. One Listener for MPT</li>
    <li>8.2.3. One Listener for ADT</li>
    </ul>
  <li>8.3. Menu bar will have four menus and a menu item: File menu, Edit menu, View menu, and About menu item
    <ul>
    <li>8.3.1. File Men: Load, Save, Edit 
    <li>8.3.1. MPT
      <ul>
      <li> Create map: x rows by y columns
      <li> Update a map grid square Feature: add single Stone, Pit, or Natural
      <li> Update a row of map grid squares: add a whole row of features
      <li> Add Find: pot, char, or metalwork, or children to map
      <li> "Mark a map grid square 'heritage'"
      </ul>
    <li>8.3.2. ADT
      <ul>
      <li> Scan map grid with magnetometer
      <li> Scan map grid with metal detector
      <li> Dig map grid with a trowel
      </ul>
    </ul>
  <li>8.4. View Menu Item
    <ul>
    <li> 8.4.1. MPT
      <ul>
      <li> Update Map Symbol: choose the symbol for display
      <li> View Current Symbol Map: show current map
      </ul>
    <li> 8.4.2. ADT
      <ul>
      <li> Update Map Symbol
      <li> View current symbol map
      <li> View magnetometer map
      <li> View metal detector map
      <li> View pottery finds map
      <li> View charcoal finds map
      <li> View metalwork map
      <li> View report
      </ul>
    </ul>
  </ul>
<li>9. Print a report</li>
  <ul>
  <li>9.1. "View Report" -- First pottery, sorted by date, then charcoal, sorted by date, then metalobjects sorted by date</li>
      <ul>
      <li> -- Straight from the project description: "...what is found, how much, their dates, and their properties have to be displayed to the screen."
      </ul>
  <li>9.1.1. Row and column</li>
  <li>9.1.2. Need special properties</li>
  <li>9.1.3. Average date displayed at the bottom</li>
  </ul>
<li>10. Create a map that's at least 50% full of archaeological features and finds</li>
<li>11. Finish UML diagrams</li>

</ul>


<h1>Coding Standard</h1>
- Variables camel case
- Class names written with the first letter of each word in caps
- Class and variables in plain language
- Any member variables of a class have to be private
- Javadoc
