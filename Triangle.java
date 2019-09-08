/****************************************************************************
 * * Created by: Heejo Suh
 * Created on: Mar 2018
 * Created for: learning
 * 
 * This class is a 'blueprint' for Triangle
 * Calculates and returns values of given triangles
 * 
 ****************************************************************************/


public class Triangle{
	//class for triangle
	
	//Private Fields
	
	private double _sideA = 0;
	private double _sideB = 0;
	private double _sideC = 0;


	private double _angleA = 0;
	private double _angleB = 0;
	private double _angleC = 0;
	

	private double _semiperimeter;
	
	private double _area;
	private String _typeName;
	

	
	public Triangle(String typeA, double a, String typeB, double b, String typeC, double c){
		//Pass parameters
		
		
		//set input Values
		setValues(typeA, a);
		setValues(typeB, b);
		setValues(typeC, c);
		
		
		//turn angles to radians so can work with them
		_angleA = Math.toRadians(_angleA);
		_angleB = Math.toRadians(_angleB);
		_angleC = Math.toRadians(_angleC);
				
		
		
		
		while (_sideA==0 || _sideB==0 || _sideC==0 || _angleA==0 || _angleB==0 || _angleC==0 ) {
			//loop until every value is put
			
			
			//SIDE A-----------------------------------------------------------
			if (_sideA==0) {
				if (_angleA!=0 &&_sideB!=0 && _sideC!=0) {
					//pythagorean
					_sideA =  Math.pow(_sideB*_sideB + _sideC*_sideC - 2*_sideB*_sideC*Math.cos(_angleA),0.5);
				} else if (_sideB!=0 && _angleB!=0 && _angleA!=0){
					//sin law
					_sideA= (_sideB*Math.sin(_angleA))/Math.sin(_angleB);
				} else if (_sideC!=0 && _angleC!=0 && _angleA!=0){
					//sin law
					_sideA= (_sideC*Math.sin(_angleA))/Math.sin(_angleC);
				}
			}
			
			//SIDE B-----------------------------------------------------------
			if (_sideB==0) {	
				if (_angleB!=0 &&_sideA!=0 && _sideC!=0) {
					//pythagorean
					_sideB =  Math.pow((_sideA*_sideA + _sideC*_sideC - 2*_sideA*_sideC*Math.cos(_angleB)),0.5);
				} else if (_sideA!=0 && _angleA!=0 && _angleB!=0){
					//sin law
					_sideB= (_sideA*Math.sin(_angleB))/Math.sin(_angleA);
				} else if (_sideC!=0 && _angleC!=0 && _angleB!=0){
					//sin law
					_sideB= (_sideC*Math.sin(_angleB))/Math.sin(_angleC);
				}
			}
							
			//SIDE C-----------------------------------------------------------
			if (_sideC==0) {	
				if (_angleC!=0 && _sideA!=0 && _sideB!=0) {
					//pythagorean
					_sideC = Math.pow((_sideA*_sideA + _sideB*_sideB - 2*_sideA*_sideB*Math.cos(_angleC)),0.5);
				} else if (_sideA!=0 && _angleA!=0 && _angleC!=0){
					//sin law
					_sideC= (_sideA*Math.sin(_angleC))/Math.sin(_angleA);
				} else if (_sideB!=0 && _angleB!=0 && _angleC!=0){
					//sin law
					_sideC= (_sideB*Math.sin(_angleC))/Math.sin(_angleB);
				}
			}
			
			
			//ANGLE A-----------------------------------------------------------
			if (_angleA == 0) {
				if (_angleB!=0 && _angleC!=0) {
					//180
					_angleA = Math.PI - _angleB - _angleC;
				} else if (_sideB!=0 && _angleB!=0 && _sideA!=0){
					//sin law
					_angleA = Math.asin((Math.sin(_angleB)*_sideA)/_sideB);
				} else if (_sideC!=0 && _angleC!=0 && _sideA!=0){
					//sin law
					_angleA =Math.asin((Math.sin(_angleC)*_sideA)/_sideC);
				} else if (_sideA!=0 && _sideB!=0 && _sideC!=0) {
					//cos law
					_angleA = Math.acos(((_sideB*_sideB) + (_sideC*_sideC) - (_sideA*_sideA))/(2*(_sideB*_sideC))) ;
				}
			}
			//ANGLE B-----------------------------------------------------------
			if (_angleB == 0) {
				if (_angleA!=0 && _angleC!=0) {
					//180
					_angleB = Math.PI - _angleA - _angleC;
				} else if (_sideA!=0 && _angleA!=0 && _sideB!=0){
					//sin law
					_angleB = Math.asin((Math.sin(_angleA)*_sideB)/_sideA);
				} else if (_sideC!=0 && _angleC!=0 && _sideB!=0){
					//sin law
					_angleB = Math.asin((Math.sin(_angleC)*_sideB)/_sideC);
				} else if (_sideA!=0 && _sideB!=0 && _sideC!=0) {
					//cos law
					_angleB = Math.acos((_sideA*_sideA + _sideC*_sideC - _sideB*_sideB)/(2*_sideA*_sideC)) ;
				}
			}
			
			//ANGLE C-----------------------------------------------------------
			if (_angleC == 0) {
				if (_angleA!=0 && _angleB!=0) {
					//180
					_angleC = Math.PI - _angleA - _angleB;
				} else if (_sideA!=0 && _angleA!=0 && _sideC!=0){
					//sin law
					_angleC = Math.asin((Math.sin(_angleA)*_sideC)/_sideA);
				} else if (_sideB!=0 && _angleB!=0 && _sideC!=0){
					//sin law
					_angleC = Math.asin((Math.sin(_angleB)*_sideC)/_sideB);
				} else if (_sideA!=0 && _sideB!=0 && _sideC!=0) {
					//cos law
					_angleC = Math.acos((_sideB*_sideB + _sideA*_sideA - _sideC*_sideC)/(2*_sideB*_sideA));
				}
			}
			
		}
		
		
		
		

		double toDegree = (180/Math.PI);
		
		//round each value
		_sideA = round(_sideA);
		_sideB = round(_sideB);
		_sideC = round(_sideC);
			
		_angleA = round(_angleA * toDegree);
		_angleB = round(_angleB * toDegree);
		_angleC = round(_angleC * toDegree);
				
		
		_semiperimeter = 0.5 * (_sideA + _sideB +_sideC);
		
		
		System.out.println("----------------------------");
		System.out.println("Side A: " + _sideA + "	Side B: " + _sideB + "	Side C: " + _sideC);
		System.out.println("Angle A: " + _angleA + "	Angle B: " + _angleB + "	Angle C: " + _angleC);
		
	} 		
	

	//TRIANGLE info set------------------------------------------------------------------------------------------
	protected void setValues(String inputType, double inputDouble){
		//sets the number as the appropriate variable value
		if (inputType.equals("SA")) {
			_sideA = inputDouble;
		}
		if (inputType.equals("SB")) {
			_sideB = inputDouble;
		}
		if (inputType.equals("SC")) {
			_sideC = inputDouble;
		}
		if (inputType.equals("AA")) {
			_angleA = inputDouble;
		}
		if (inputType.equals("AB")) {
			_angleB = inputDouble;
		}
		if (inputType.equals("AC")) {
			_angleC = inputDouble;
		}
	}	

	
	//TRIANGLE VALID------------------------------------------------------------------------------------------
	protected double round(double inputDouble){
		//returns rounded number
		return Double.valueOf(String.format("%.2f", inputDouble));
	}	
	

	
	//TRIANGLE VALID------------------------------------------------------------------------------------------
	protected boolean IsTriangleValid(){
		//check if triangle is valid or not
		
		boolean triangleValidity;
		
		if ((_angleA+_angleB+_angleC)==180 && ((_sideB +_sideC)>_sideA && (_sideA +_sideC)>_sideB && (_sideA + _sideB)>_sideC)){
			//sum of angles is equal to 180 & sum of two sides are longer than the other
			triangleValidity = true;
			
		} else {
			triangleValidity = false;
		}

		return triangleValidity;
	}

	
	

	//AREA OF TRIANGLE-----------------------------------------------------------------------------------------
	public double GetArea() { 
		//calculates and returns the area
		
		//area = ( s(s-a)(s-b)(s-c) ) ^ 0.5
		_area = Math.sqrt((_semiperimeter*(_semiperimeter - _sideA)*(_semiperimeter - _sideB)*(_semiperimeter - _sideC)));
		//return Math.round(_area);
		return round(_area);
	}		

	//TRIANGLE TYPE-----------------------------------------------------------------------------------------------
	public String GetName() { 
		//calculates and returns the triangle type
		_typeName = "";
		
		//angles
		//Math.pow(side,2)
		if (_angleA == 90.0 || _angleB == 90.0 || _angleC == 90.0 ){
			//one right angle
			_typeName = "Right-angle ";
		} else if (_angleA > 90 || _angleB > 90 || _angleC > 90 ){
			//one angle larger than 90
			_typeName = "Obtuse ";
		} else if (_angleA==_angleB && _angleA==_angleC){
			//all angles equal
			_typeName = "Equiangular ";
		} else if (_angleA < 90 && _angleB < 90 && _angleC < 90 ){
			//all angles less than 90
			_typeName = "Acute ";
			
		} 
			
			
			
		//sides
		if (_sideA == _sideB && _sideA == _sideC){
			//all sides equal
			_typeName += "Equilateral";
			
		} else if (_sideA == _sideB || _sideB == _sideC || _sideA == _sideC){
			//two sides equal
			_typeName += "Isosceles";
			
		} else {
			//else
			//all sides different
			_typeName += "Scalene";
		}
		
		
		return _typeName;
	}		
	
	
	//HEIGHT-----------------------------------------------------------------------------------------------------
	public void printTriangleHeight(){
		//calculates and prints the heights
		//A = 0.5*bh
		//h = 2A / b
		double heightA = _area*2 / _sideA;
		double heightB = _area*2 / _sideB;
		double heightC = _area*2 / _sideC;
		
		//just print here-> less work
		System.out.println("Heights: " + round(heightA) + ", " + round(heightB) + ", " + round(heightC));
	}
	
	//LARGEST INSCRIBED CIRCLE---------------------------------------------------------------------------------------
	public void printLargestInscribedCircle(){
		//calculates and prints the radius of the largest inscribed circle
		
		//radius= area of triangle / semiperimeter
		double radius = _area / _semiperimeter;
		
		System.out.println("The radius of the largest inscribed circle that can fit inside the triangle: " + round(radius));
		
	}
	
	//CIRCMCIRCLE----------------------------------------------------------------------------------------------------
	public void printAreaOfTheCircumcircleOfTheTriangle(){
		//calculates and returns the circumcircle
		//radius = (a+b+c)/(4*Area)
		double radiusOfCircumcircleOfTheTriangle = _sideA*_sideB*_sideC / (4 * _semiperimeter);
		double areaOfCircumcircleOfTheTriangle = Math.PI * Math.pow(radiusOfCircumcircleOfTheTriangle, 2);
		
		System.out.println("The area of the circumcircle of the triangle: " + round(areaOfCircumcircleOfTheTriangle));
	}
	//----------------------------------------------------------------------------------------------------
	
}//closing for class
