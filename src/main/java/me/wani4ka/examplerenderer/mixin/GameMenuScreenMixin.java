package me.wani4ka.examplerenderer.mixin;

import me.wani4ka.examplerenderer.screen.ExampleScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.Positioner;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {

    @Redirect(method = "initWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;ILnet/minecraft/client/gui/widget/Positioner;)Lnet/minecraft/client/gui/widget/Widget;"))
    private <T extends Widget> T addMyScreen(GridWidget.Adder instance, T widget, int occupiedColumns, Positioner positioner) {
        var ans = instance.add(widget, occupiedColumns, positioner);
        instance.add(ButtonWidget.builder(Text.of("Test screen"), button -> {
            var client = MinecraftClient.getInstance();
            client.setScreen(new ExampleScreen(client.currentScreen));
        }).width(204).build(), 2);
        return ans;
    }

}
