package project.salesmanager.model;

public class Sale
{
  private int id;
  private String item;
  private int quantity;
  private float amount;

  public Sale(String item, int quantity, float amount)
  {
    this.item = item;
    this.quantity = quantity;
    this.amount = amount;
  }

  public Sale()
  {
  }

  public int getId()
  {
    return id;
  }

  public Sale setId(int id)
  {
    this.id = id;
    return this;
  }

  public String getItem()
  {
    return item;
  }

  public Sale setItem(String item)
  {
    this.item = item;
    return this;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public Sale setQuantity(int quantity)
  {
    this.quantity = quantity;
    return this;
  }

  public float getAmount()
  {
    return amount;
  }

  public Sale setAmount(float amount)
  {
    this.amount = amount;
    return this;
  }

  @Override
  public String toString()
  {
    return "Sale{" +
        "id=" + id +
        ", item='" + item + '\'' +
        ", quantity=" + quantity +
        ", amount=" + amount +
        '}';
  }
}
