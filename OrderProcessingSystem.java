public class OrderProcessingSystem {

    Node head = null;
    Node HistoryHead = null;
    static class Node{
        int OrderID;
        String Customer_Name;
        String OrderDetails;
        String status;
        Node next = null;
        public Node(int OrderID ,String Customer_Name ,String OrderDetails){
            this.OrderID = OrderID;
            this.Customer_Name = Customer_Name;
            this.OrderDetails = OrderDetails;
            status = "waiting";
            next = null;
        }
    }

    public boolean inStock(String detail){
        String[] items ={"Phone","Laptop","Headphones","TV","Charger"};
        for(int i=0;i< items.length;i++){
            if(detail.equals(items[i])){
                return true;
            }
        }
        return false;
    }

    public void  AddOrder(int ID,String name, String details){
        if(inStock(details)){
            Node newNode = new Node(ID, name, details);
            Node temp = head;
            if(temp == null){
                head = newNode;
                System.out.println("Order Has Been Add...");
            }else{
                while(temp.next != null){
                    temp = temp.next;
                    }
                temp.next = newNode;
                System.out.println("Order Has Been Add...");
            }
        }else{
            System.out.println("[Item Out Of Stock !]\n");
        }
    }

    public void AddPriority(int ID ,String name ,String details){
        if(inStock(details)){
            Node newNode = new Node(ID,name,details);
            if(head == null){
                head = newNode;
                System.out.println("Priority Order Has Been Add...");
                return;
            }
            newNode.status = "Priority";
            newNode.next = head;
            head = newNode;
            System.out.println("Priority Order Has Been Add...");
        }else{
            System.out.println("[Item Out Of Stock !]\n");
        }
    }

    public void FulfillOrder(){
        if(head == null){
            System.out.println("There Is No Order");
            System.out.println();
            return;
        }
        Node temp = head;
        head = head.next;
        temp.status ="Done";
        System.out.println("| Customer Name: "+temp.Customer_Name+" | Order ID: "+temp.OrderID+" | Order Details: "+temp.OrderDetails+" | Order Status: "+temp.status+" |\n");
        AddOrderHistory(temp.Customer_Name,temp.OrderID,temp.OrderDetails,temp.status);
    }

    public void ViewOrder(){
        Node temp = head;
        if(temp == null){
            System.out.println("There Is No Order");
            System.out.println();
        }else{
            System.out.println("---{ Order List }---");
            while(temp != null){
                System.out.println("| Customer Name: "+temp.Customer_Name+" | Order ID: "+temp.OrderID+" | Order Details: "+temp.OrderDetails+" | Order Status: "+temp.status+" |");
                temp = temp.next;
            }
            System.out.println("--------------------\n");
        }

    }

    public void CancelOrder(int id){
        Node temp = head;
        if(temp == null){
            System.out.println("There Is No Order");
            return;
        }

        if(head.OrderID == id){
            head = head.next;
            temp.status = "Cancel";
            System.out.println("| Customer Name: "+temp.Customer_Name+" | Order ID: "+temp.OrderID+" | Order Details: "+temp.OrderDetails+" | Order Status: "+temp.status+" |\n");
            AddOrderHistory(temp.Customer_Name,temp.OrderID,temp.OrderDetails,temp.status);
            return;
        }

        while(temp.next != null && temp.next.OrderID != id){
            temp = temp.next;
        }

        if(temp.next == null){
            System.out.println("There Is No Order by this ID");
            System.out.println();
        }else{
            temp.next.status = "Cancel";
            temp = temp.next.next;
            System.out.println("| Customer Name: "+temp.Customer_Name+" | Order ID: "+temp.OrderID+" | Order Details: "+temp.OrderDetails+" | Order Status: "+temp.status+" |");
            System.out.println();
            AddOrderHistory(temp.Customer_Name,temp.OrderID,temp.OrderDetails,temp.status);
        }

    }

    public void AddOrderHistory(String Customer_Name,int id,String OrderDetails ,String status){
        Node newHistory = new Node(id,Customer_Name,OrderDetails);
        newHistory.status = status;
        Node temp = HistoryHead;
        if(HistoryHead == null){
            HistoryHead = newHistory;
        }
        else{
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newHistory;
        }


    }

    public void ViewHistory(){
        Node temp = HistoryHead;
        if(temp == null){
            System.out.println("No History Yet");
        }else{
            System.out.println("---{ Order History }---");
            while(temp != null){
                System.out.println("| Customer Name: "+temp.Customer_Name+" | Order ID: "+temp.OrderID+" | Order Details: "+temp.OrderDetails+" | Order Status: "+temp.status+" |");
                temp = temp.next;
            }
            System.out.println("-----------------------\n");

        }
    }

    public static void main(String[] args) {
        OrderProcessingSystem order = new OrderProcessingSystem();

        System.out.println();

        order.AddOrder(101, "Ahmed", "Phone");
        order.AddOrder(102, "Noor", "Laptop");
        order.AddOrder(103, "Ali", "Headphones");

        System.out.println();

        order.ViewOrder();

        order.FulfillOrder();

        order.CancelOrder(102);

        order.ViewOrder();
        order.ViewHistory();

        order.AddPriority(104,"Mohammed","TV");

        System.out.println();

        order.ViewOrder();

        order.FulfillOrder();

        order.ViewOrder();
        order.ViewHistory();

        order.AddOrder(105, "Fars", "Car");

        order.ViewHistory();

        order.FulfillOrder();

        order.ViewOrder();
        order.ViewHistory();

    }
}