import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//p14086kousounnisKwnstantinos
//p14036GiwrgosDiwtis

public class BaseisDedomenwnErg {
   public static void main( String args[] ) {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "pappoulis13");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         System.out.println("Fitsr Query answer (a)");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "select repairs.id_car,sum(cost_repair),cars.model\r\n" + 
         		"from repairs \r\n" + 
         		"inner join cars\r\n" + 
         		"on repairs.id_car=cars.id_car\r\n" + 
         		"group by repairs.id_car,cars.model\r\n" + 
         		"having sum(cost_repair) in(\r\n" + 
         		"select max(sum_cost)\r\n" + 
         		"from(select id_car,sum(cost_repair)as sum_cost from repairs group by id_car)as template)" );
        
         System.out.println( "a)The cars with the maximum cost repair are: \n" );
         while ( rs.next() ) {
             
            String  id_car = rs.getString("id_car");
            int sum_cost_repair=rs.getInt("sum");
            String model=rs.getString("model");
           
           
            
            System.out.println( "id_car = " + id_car );
            System.out.println( "sum(cost_repair) = " + sum_cost_repair );
            System.out.println( "Model = " + model );
            
            System.out.println();
         }
         
         System.out.println("Second Query answer (b)");
         stmt = c.createStatement();
          rs = stmt.executeQuery( " select date_trunc('month',date_repair_starts),avg(cost_repair)as Avg_earnings_bymonth\r\n" + 
          		"from repairs\r\n" + 
          		"group by date_trunc('month', date_repair_starts)\r\n" + 
          		"order by  date_trunc('month', date_repair_starts)" );
        
         System.out.println( "b)The average profit from repairs by month is: \n" );
         while ( rs.next() ) {
             
             String  date_trunc = rs.getString("date_trunc");
             double  Avg_earnings_bymonth=rs.getDouble("Avg_earnings_bymonth");
            
            
             
             System.out.println( "date_trunc = " + date_trunc );
             System.out.println( "Avg_earnings_bymonth = " + Avg_earnings_bymonth );
             
             System.out.println();
          }
         
         
         
         
         System.out.println("Third Query answer (c)");
         stmt = c.createStatement();
          rs = stmt.executeQuery( "select sum(car_sold-car_value) ,sells.id_seller,sellers.afm,company.Fullname\r\n" + 
          		"from sells \r\n" + 
          		"inner join sellers\r\n" + 
          		"on sells.id_seller=sellers.id_seller\r\n" + 
          		"inner join company\r\n" + 
          		"on sellers.afm=company.afm\r\n" + 
          		"group by sells.id_seller,sellers.afm,company.Fullname\r\n" + 
          		"having sum(car_sold-car_value)in(\r\n" + 
          		"select max(difference)\r\n" + 
          		"from(select id_seller,sum(car_sold-car_value)as difference	from sells group by id_seller)as template)\r\n" + 
          		"" );
        
         System.out.println( "c)Who is the seller with the biggest profits: \n" );
         while ( rs.next() ) {
             
             int Sum = rs.getInt("Sum");
             String id_seller=rs.getString("id_seller");
             double afm=rs.getDouble("afm");
             String fullname=rs.getString("fullname");
            
             
             System.out.println( "Sum profits = " + Sum ); 
             System.out.println( "id_seller = " + id_seller );
             System.out.println( "afm = " + afm );
             System.out.println( "fullname = " + fullname );
             
             
             System.out.println();
          }
         
         System.out.println("Fourth Query answer (d)");
         stmt = c.createStatement();
          rs = stmt.executeQuery( " select id_car,repairs.date_repair_starts \r\n" + 
          		"from repairs\r\n" + 
          		"where  repairs.date_repair_starts not in (select date_ends.date_repair_starts from date_ends )" );
        
         System.out.println( "d)The repairs which are not copleted yet: \n" );
         while ( rs.next() ) {
             
             String id_car = rs.getString("id_car");
             String date_repair_starts=rs.getString("date_repair_starts");
             
            
             
             System.out.println( "id_car = " + id_car ); 
             System.out.println( "date_repair_starts = " + date_repair_starts );
            
             
             
             System.out.println();
          }
         
         
         
         
         
         System.out.println("Fifth Query answer (e)");
         stmt = c.createStatement();
          rs = stmt.executeQuery( "select technicians.id_tech ,repairs.date_repair_starts,repairs.id_car,repairs.cost_repair,technicians.afm,company.fullname\r\n" + 
          		"from repairs\r\n" + 
          		"inner join technicians\r\n" + 
          		"on repairs.id_tech=technicians.id_tech\r\n" + 
          		"inner join company\r\n" + 
          		"on technicians.afm=company.afm\r\n" + 
          		"where repairs.date_repair_starts>='2018-6-1' and repairs.date_repair_starts<='2018-6-30'\r\n" + 
          		"order by repairs.id_tech\r\n" + 
          		"  " );
        
         System.out.println( "e)The jobs of 'x' technician the last month : \n" );
         while ( rs.next() ) {
             
             String id_tech = rs.getString("id_tech");
             String date_repair_starts = rs.getString("date_repair_starts");
             String id_car = rs.getString("id_car");
             int cost_repair = rs.getInt("cost_repair");
             double afm = rs.getDouble("afm");
             String fullname = rs.getString("fullname");
             
             
            
             
             System.out.println( "id_car = " + id_tech ); 
             System.out.println( "date_repair_starts = " + date_repair_starts );
             System.out.println( "id_car = " + id_car );
             System.out.println( "cost_repair = " + cost_repair );
             System.out.println( "afm = " + afm );
             System.out.println( "fullname = " + fullname );
             
             
             System.out.println();
          }
         
         System.out.println("Sixth Query answer (f)");
         stmt = c.createStatement();
          rs = stmt.executeQuery( "  select count(repair_cars18.id_car) ,repair_cars18.id_car,cars.model\r\n" + 
          		"from (select repairs.id_car ,repairs.date_repair_starts 	from repairs where repairs.date_repair_starts>'2018-1-1'and repairs.date_repair_starts<'2018-12-31')as repair_cars18\r\n" + 
          		"inner join cars\r\n" + 
          		"on repair_cars18.id_car=cars.id_car\r\n" + 
          		"group by   repair_cars18.id_car,cars.model\r\n" + 
          		"having count(repair_cars18.id_car)>1\r\n" + 
          		" \r\n" + 
          		"" );
        
         System.out.println( "f)The cars that have come for repairs more than one time this month are: \n" );
         while ( rs.next() ) {
             
             int count = rs.getInt("count");
             String id_car=rs.getString("id_car");
             String model=rs.getString("model");
             

             System.out.println( "count time of repairs = " + count );             
             System.out.println( "id_car = " + id_car ); 
             System.out.println( "model = " + model );  
             
             
             System.out.println();
          }
         
         
         
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Operation done successfully");
   }
}