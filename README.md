<h1>JavaProject and Other Dates</h1>

<ul>
<li>Phase One due September 29th.</li>
<li>CSE1325 Midterm on October 20th.</li>
<li>Phase Two due November 3rd.</li>
<li>Phase Three</li>
<li>CSE1325 Final</li>
</ul>


<h1>To Do for Phase Two</h1>
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
  <li>3.1. Ferrous, nonferrous</li>
  </ul>
<li>4. Change the load and save methods to the new file standard</li>
<li>5. Metalwork parent class; must be abstract and respond to metal detector</li>
<li>6. Static behavior -- one gold item on the map</li>
  <ul>
  <li>6.1. If there's a new gold object, the old one should either be overwritten or removed</li>
  </ul>
<li>7. Map coordinates can be marked heritage</li>
  <ul>
  <li>7.1. Throw custom exception if user tries to dig</li>
  </ul>
<li>8. GUI</li>
  <ul>
  <li>8.1. Figure out threading</li>
  <li>8.2. Three ActionListener</li>
    <ul>
    <li>8.2.1. One Listener for Shared (default)</li>
      <ul>8.2.1.1. This one is going to be the "layout" for the other two files - very generic; will copy and paste the other two from this and make them specific toward their respective duties.
      </ul>
    <li>8.2.2. One Listener for MPT</li>
    <li>8.2.3. One Listener for ADT</li>
    </ul>
  </ul>
<li>9. Print a report</li>
  <ul>
  <li>9.1. "View Report" -- First pottery, sorted by date, then charcoal, sorted by date, then metalobjects sorted by date</li>
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
