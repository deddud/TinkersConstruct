package slimeknights.tconstruct.tables.client.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import slimeknights.mantle.client.screen.ElementScreen;
import slimeknights.tconstruct.library.client.Icons;

public class ButtonItem<T> extends Button {

  protected static final ElementScreen BUTTON_PRESSED_GUI = new ElementScreen(144, 216, 18, 18, 256, 256);
  protected static final ElementScreen BUTTON_NORMAL_GUI = new ElementScreen(144 + 18 * 2, 216, 18, 18, 256, 256);
  protected static final ElementScreen BUTTON_HOVER_GUI = new ElementScreen(144 + 18 * 4, 216, 18, 18, 256, 256);

  private final ItemStack icon;
  public final T data;
  public boolean pressed;

  private ElementScreen pressedGui = BUTTON_PRESSED_GUI;
  private ElementScreen normalGui = BUTTON_NORMAL_GUI;
  private ElementScreen hoverGui = BUTTON_HOVER_GUI;
  private ResourceLocation backgroundLocation = Icons.ICONS;

  public ButtonItem(int x, int y, int width, int height, ITextComponent text, T data, IPressable onPress) {
    super(x, y, 18, 18, text, onPress);

    this.icon = null;
    this.data = data;
  }

  public ButtonItem(int x, int y, ItemStack icon, T data, IPressable onPress) {
    super(x, y, 18, 18, icon.getDisplayName(), onPress);

    this.icon = icon;
    this.data = data;
  }

  public ButtonItem<T> setGraphics(ElementScreen normal, ElementScreen hover, ElementScreen pressed, ResourceLocation background) {
    this.pressedGui = pressed;
    this.normalGui = normal;
    this.hoverGui = hover;
    this.backgroundLocation = background;

    return this;
  }

  @Override
  public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    Minecraft.getInstance().getTextureManager().bindTexture(this.backgroundLocation);

    if (this.visible) {
      this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

      if (this.pressed) {
        this.pressedGui.draw(matrices, this.x, this.y);
      } else if (this.isHovered) {
        this.hoverGui.draw(matrices, this.x, this.y);
      } else {
        this.normalGui.draw(matrices, this.x, this.y);
      }

      this.drawIcon(Minecraft.getInstance());
    }
  }

  protected void drawIcon(Minecraft mc) {
    mc.getItemRenderer().renderItemIntoGUI(this.icon, this.x + 1, this.y + 1);
  }
}
