package in.project;

import java.io.IOException;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.ScatterPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.HistogramTrace;
import tech.tablesaw.plotly.traces.ScatterTrace;
public class DataAnalysis {
	public static void main(String args[]) {
		System.out.println("Data Visualization");
		try {
			Table data = Table.read().csv("D:\\ellipseProjects\\in.project\\src\\main\\java\\in\\project\\training.csv");
			System.out.println(data.shape());
		     System.out.println(data.structure());
		     System.out.println(data.summary());
	
		     //HISTOGRAM
		     Layout layout1 = Layout.builder().title(" Loan Amount").build();
		     HistogramTrace trace1 = HistogramTrace.builder(data.nCol("LoanAmount")).build();
		     Plot.show(new Figure(layout1, trace1));
		     		     
		     //BOX PLOT
		     Layout layout2 = Layout.builder().title("Loan Amount v/s loan Status").build();
		     BoxTrace trace2 = BoxTrace.builder(data.categoricalColumn("Loan_Status"),data.nCol("LoanAmount")).build();
		     Plot.show(new Figure(layout2, trace2));
		     
		     //SCATTER PLOT
		     Layout layout3 = Layout.builder().title("Loan_Status v/s Applicant Income").build();
		     ScatterTrace trace3 = ScatterTrace.builder(data.nCol("ApplicantIncome"),data.nCol("Loan_Status")).build();
		     Plot.show(new Figure(layout3, trace3));
		     
		     //BAR GRAPH
		     Layout layout4 = Layout.builder().title("Property_Area v/s Loan_Status").build();
		     BarTrace trace4 = BarTrace.builder(data.categoricalColumn("Property_Area"),data.nCol("Loan_Status")).build();
		     Plot.show(new Figure(layout4,trace4));
		     
		     
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
