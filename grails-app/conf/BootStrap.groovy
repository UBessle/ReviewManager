import org.bessle.rm.QualityGroup

class BootStrap {

	def customMarshallerRegistrar
    def fixtureLoader

    def init = { servletContext ->
		customMarshallerRegistrar.registerMarshallers()

        // load QualityGroup fixture, if QualityGroup database table is empty
        if (QualityGroup.count == 0) {
            fixtureLoader.load("org/bessle/rm/QualityGroupFixture")
        }
	}
    def destroy = {
    }

}
